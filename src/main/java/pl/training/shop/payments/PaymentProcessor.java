package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;
import pl.training.shop.commons.aop.Length;
import pl.training.shop.commons.aop.Lock;
import pl.training.shop.commons.aop.LogExecutionTime;
import pl.training.shop.commons.aop.Retry;
import pl.training.shop.time.TimeProvider;

import java.time.Instant;

@RequiredArgsConstructor
public class PaymentProcessor implements PaymentService {

    private static final PaymentStatus DEFAULT_PAYMENT_STATUS = PaymentStatus.STARTED;

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentFeeCalculator paymentFeeCalculator;
    private final PaymentRepository paymentsRepository;
    private final TimeProvider timeProvider;

    @Lock
    @Retry(attempts = 2)
    @LogExecutionTime
    @LogPayment
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var paymentValue = calculateTotalPaymentValue(paymentRequest.getValue());
        var payment = createPayment(paymentValue);
        return paymentsRepository.save(payment);
    }

    private Payment createPayment(FastMoney paymentValue) {
        return Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentValue)
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
    }

    private FastMoney calculateTotalPaymentValue(FastMoney paymentValue) {
        return paymentValue.add(paymentFeeCalculator.calculateFee(paymentValue));
    }

    @Override
    public Payment getById(@Length String id) {
        return paymentsRepository.getById(id)
                .orElseThrow(PaymentNotFoundException::new);
    }

}
