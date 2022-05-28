package pl.training.payments.ports;

public interface GetPaymentUseCase {

    Payment getById(String id);

    ResultPage<Payment> getByStatus(PaymentStatus status, Page page);

}
