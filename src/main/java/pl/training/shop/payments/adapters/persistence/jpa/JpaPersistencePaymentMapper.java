package pl.training.shop.payments.adapters.persistence.jpa;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.payments.domain.Payment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JpaPersistencePaymentMapper {

    @Mapping(target = "value", expression = "java(java.math.BigDecimal.valueOf(payment.getValue().getNumber().doubleValueExact()))")
    @Mapping(target = "currency", expression = "java(payment.getValue().getCurrency().getCurrencyCode())")
    PaymentEntity toEntity(Payment payment);

    @Mapping(target = "value", expression = "java(org.javamoney.moneta.FastMoney.of(entity.getValue(), entity.getCurrency()))")
    Payment toDomain(PaymentEntity entity);

    @IterableMapping(elementTargetType = Payment.class)
    List<Payment> toDomain(List<PaymentEntity> entities);

    ResultPage<Payment> toDomain(ResultPage<PaymentEntity> page);

}
