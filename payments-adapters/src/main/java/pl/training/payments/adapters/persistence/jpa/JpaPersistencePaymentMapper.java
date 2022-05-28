package pl.training.payments.adapters.persistence.jpa;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.commons.money.Currency;
import pl.training.payments.ports.Payment;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring", imports = {BigDecimal.class, Currency.class})
public interface JpaPersistencePaymentMapper {

    @Mapping(target = "value", expression = "java(new BigDecimal(payment.getValue().split(Currency.CURRENCY_SEPARATOR)[1]))")
    @Mapping(target = "currency", expression = "java(payment.getValue().split(Currency.CURRENCY_SEPARATOR)[0])")
    PaymentEntity toEntity(Payment payment);

    @Mapping(target = "value", expression = "java(entity.getValue() + Currency.CURRENCY_SEPARATOR + entity.getCurrency())")
    Payment toDomain(PaymentEntity entity);


    @IterableMapping(elementTargetType = Payment.class)
    List<Payment> toDomain(List<PaymentEntity> entities);

}
