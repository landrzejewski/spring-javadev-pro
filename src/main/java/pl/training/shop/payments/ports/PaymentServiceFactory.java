package pl.training.shop.payments.ports;

public interface PaymentServiceFactory {

    PaymentService create(PaymentRepository paymentRepository, TimeProvider timeProvider);

}
