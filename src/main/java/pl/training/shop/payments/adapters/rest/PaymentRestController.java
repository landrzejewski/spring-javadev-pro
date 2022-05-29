package pl.training.shop.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.web.ExceptionDto;
import pl.training.shop.commons.web.LocationUri;
import pl.training.shop.commons.web.ResultPageDto;
import pl.training.shop.payments.domain.PaymentNotFoundException;
import pl.training.shop.payments.ports.PaymentService;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static pl.training.shop.payments.domain.PaymentStatus.CONFIRMED;

@RequestMapping("api/payments")
@RestController
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentService paymentService;
    private final RestPaymentMapper mapper;

    @PostMapping
    public ResponseEntity<PaymentDto> process(@RequestBody PaymentRequestDto paymentRequestDto) {
        var paymentRequest = mapper.toDomain(paymentRequestDto);
        var payment = paymentService.process(paymentRequest);
        var paymentDto = mapper.toDto(payment);
        var locationUri = LocationUri.fromRequest(payment.getId());
        return ResponseEntity.created(locationUri).body(paymentDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable String id) {
        var payment = paymentService.getById(id);
        var paymentDto = mapper.toDto(payment);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping
    public ResponseEntity<ResultPageDto<PaymentDto>> getConfirmedPayments(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "5") int pageSize) {
        var page = mapper.toDomain(pageNumber, pageSize);
        var resultPage = paymentService.getByStatus(CONFIRMED, page);
        var resultPageDto = mapper.toDto(resultPage);
        return ResponseEntity.ok(resultPageDto);
    }

    /*@ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionDto> onPaymentNotFound(PaymentNotFoundException exception) {
        return ResponseEntity.status(NOT_FOUND)
                .body(new ExceptionDto("Payment not found"));
    }*/

}
