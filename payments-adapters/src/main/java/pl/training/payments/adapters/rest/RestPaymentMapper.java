package pl.training.payments.adapters.rest;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import pl.training.commons.money.FastMoneyMapper;
import pl.training.payments.adapters.commons.web.ResultPageDto;
import pl.training.payments.ports.Payment;
import pl.training.payments.ports.PaymentRequest;
import pl.training.payments.ports.PaymentStatus;
import pl.training.payments.ports.ResultPage;

import java.util.List;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface RestPaymentMapper {

    @Mapping(source = "requestId", target = "id")
    PaymentRequest toDomain(PaymentRequestDto dto);

    PaymentDto toDto(Payment payment);

    @ValueMapping(source = "STARTED", target = "NOT_CONFIRMED")
    @ValueMapping(source = "FAILED", target = "NOT_CONFIRMED")
    @ValueMapping(source = "CANCELED", target = "NOT_CONFIRMED")
    String toDto(PaymentStatus status);

    @IterableMapping(elementTargetType = PaymentDto.class)
    List<PaymentDto> toDto(List<Payment> payments);

    ResultPageDto<PaymentDto> toDto(ResultPage<Payment> resultPage);

}
