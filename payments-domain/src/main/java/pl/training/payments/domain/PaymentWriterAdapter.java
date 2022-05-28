package pl.training.payments.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.training.payments.ports.PaymentWriter;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PaymentWriterAdapter {

    private final PaymentWriter paymentWriter;
    private final PaymentDomainMapper mapper;

    PaymentDomain save(PaymentDomain paymentDomain) {
        var payment = mapper.toPorts(paymentDomain);
        return mapper.toDomain(paymentWriter.save(payment));
    }

}
