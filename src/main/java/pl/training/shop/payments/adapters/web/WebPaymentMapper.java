package pl.training.shop.payments.adapters.web;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import pl.training.shop.commons.FastMoneyMapper;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.commons.web.ResultPageDto;
import pl.training.shop.commons.web.ResultPageViewModel;
import pl.training.shop.payments.adapters.rest.PaymentDto;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.domain.PaymentRequest;

import java.util.List;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface WebPaymentMapper {

    PaymentRequest toDomain(PaymentRequestViewModel viewModel);

    PaymentViewModel toViewModel(Payment payment);

    default Page toDomain(int pageNumber, int pageSize) {
        return new Page(pageNumber, pageSize);
    }

    @IterableMapping(elementTargetType = PaymentDto.class)
    List<PaymentViewModel> toViewModel(List<Payment> payments);

    ResultPageViewModel<PaymentViewModel> toViewModel(ResultPage<Payment> paymentResultPage);

}
