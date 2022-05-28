package pl.training.payments.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.training.payments.ports.Page;
import pl.training.payments.ports.PaymentReader;
import pl.training.payments.ports.ResultPage;

import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PaymentReaderAdapter {

    private final PaymentReader paymentReader;
    private final PaymentDomainMapper mapper;

    Optional<PaymentDomain> getById(String id) {
        return paymentReader.getById(id)
                .map(mapper::toDomain);
    }

    ResultPage<PaymentDomain> getByStatus(PaymentStatusDomain status, Page page) {
        var paymentStatus = mapper.toPorts(status);
        return mapper.toDomain(paymentReader.getByStatus(paymentStatus, page));
    }

}
