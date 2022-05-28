package pl.training.shop.payments.domain;

import pl.training.shop.payments.ports.PaymentRepository;
import pl.training.shop.payments.ports.PaymentService;
import pl.training.shop.payments.ports.PaymentServiceFactory;
import pl.training.shop.payments.ports.TimeProvider;

public class DefaultPaymentServiceFactory implements PaymentServiceFactory {

    private static final PaymentIdGenerator PAYMENT_ID_GENERATOR = new UuidPaymentIdGenerator();
    private static final PaymentFeeCalculator PAYMENT_FEE_CALCULATOR = new PercentagePaymentFeeCalculator(0.01);

    @Override
    public PaymentService create(PaymentRepository paymentRepository, TimeProvider timeProvider) {
        return new PaymentProcessor(PAYMENT_ID_GENERATOR, PAYMENT_FEE_CALCULATOR, paymentRepository, timeProvider);
    }

}
