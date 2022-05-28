package pl.training.payments.ports;

import java.util.Optional;

public interface PaymentReader {

    Optional<Payment> getById(String id);

    ResultPage<Payment> getByStatus(PaymentStatus status, Page page);

}
