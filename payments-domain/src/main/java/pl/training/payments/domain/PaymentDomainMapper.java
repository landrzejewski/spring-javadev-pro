package pl.training.payments.domain;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import pl.training.commons.money.FastMoneyMapper;
import pl.training.payments.ports.Payment;
import pl.training.payments.ports.PaymentRequest;
import pl.training.payments.ports.PaymentStatus;
import pl.training.payments.ports.ResultPage;

import java.util.List;

@Mapper(uses = FastMoneyMapper.class)
interface PaymentDomainMapper {

    PaymentDomain toDomain(Payment payment);

    Payment toPorts(PaymentDomain paymentDomain);

    PaymentRequestDomain toDomain(PaymentRequest paymentRequest);

    PaymentStatus toPorts(PaymentStatusDomain paymentStatusDomain);

    PaymentStatusDomain toDomain(PaymentStatus paymentStatus);

    @IterableMapping(elementTargetType = PaymentDomain.class)
    List<PaymentDomain> toDomain(List<Payment> payments);

    ResultPage<Payment> toPorts(ResultPage<PaymentDomain> paymentDomainResultPage);

    ResultPage<PaymentDomain> toDomain(ResultPage<Payment> paymentResultPage);

}
