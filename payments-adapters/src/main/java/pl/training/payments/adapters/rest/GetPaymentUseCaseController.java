package pl.training.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.payments.adapters.commons.web.ResultPageDto;
import pl.training.payments.ports.GetPaymentUseCase;
import pl.training.payments.ports.Page;

import static pl.training.payments.ports.PaymentStatus.CONFIRMED;

@RequestMapping("api/payments")
@RestController
@RequiredArgsConstructor
public class GetPaymentUseCaseController {

    private final GetPaymentUseCase getPaymentUseCase;
    private final RestPaymentMapper paymentMapper;

    @GetMapping("{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable String id) {
        var payment = getPaymentUseCase.getById(id);
        var paymentDto = paymentMapper.toDto(payment);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping("confirmed")
    public ResponseEntity<ResultPageDto<PaymentDto>> getConfirmedPayments(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "5") int pageSize) {
        var page = new Page(pageNumber, pageSize);
        var resultPage = getPaymentUseCase.getByStatus(CONFIRMED, page);
        var resultPageDto = paymentMapper.toDto(resultPage);
        return ResponseEntity.ok(resultPageDto);
    }

}
