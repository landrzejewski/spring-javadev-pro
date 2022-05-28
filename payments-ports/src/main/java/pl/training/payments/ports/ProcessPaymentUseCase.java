package pl.training.payments.ports;

public interface ProcessPaymentUseCase {

    Payment process(PaymentRequest paymentRequest);

}
