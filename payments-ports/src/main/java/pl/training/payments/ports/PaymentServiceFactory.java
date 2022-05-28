package pl.training.payments.ports;

public interface PaymentServiceFactory {

    GetPaymentUseCase create(PaymentReader paymentReader);

    ProcessPaymentUseCase create(PaymentWriter paymentWriter, TimeProvider timeProvider);

}
