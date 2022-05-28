package pl.training.payments.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;
import pl.training.payments.ports.*;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ProcessPaymentService {

    private static final PaymentStatusDomain DEFAULT_PAYMENT_STATUS = PaymentStatusDomain.CONFIRMED;

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentFeeCalculator paymentFeeCalculator;
    private final PaymentWriterAdapter paymentWriter;
    private final TimeProvider timeProvider;

    PaymentDomain process(PaymentRequestDomain paymentRequestDomain) {
        var paymentValue = calculatePaymentValue(paymentRequestDomain.getValue());
        var payment = createPayment(paymentValue);
        return paymentWriter.save(payment);
    }

    private PaymentDomain createPayment(FastMoney paymentValue) {
        return PaymentDomain.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentValue)
                .timestamp(timeProvider.getTimestamp())
                .status(DEFAULT_PAYMENT_STATUS)
                .build();
    }

    private FastMoney calculatePaymentValue(FastMoney paymentValue) {
        return paymentValue.add(paymentFeeCalculator.calculateFee(paymentValue));
    }

}
