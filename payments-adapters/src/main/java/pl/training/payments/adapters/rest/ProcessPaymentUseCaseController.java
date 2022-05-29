package pl.training.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.training.payments.adapters.commons.validation.Extended;
import pl.training.payments.adapters.commons.web.LocationUri;
import pl.training.payments.ports.ProcessPaymentUseCase;

@RequestMapping("api/payments")
@RestController
@RequiredArgsConstructor
public class ProcessPaymentUseCaseController {

    private final ProcessPaymentUseCase processPaymentUseCase;
    private final RestPaymentMapper paymentMapper;

    @PostMapping
    public ResponseEntity<PaymentDto> process(/*@Valid*/ @Validated(Extended.class) @RequestBody PaymentRequestDto paymentRequestDto) {
        var paymentRequest = paymentMapper.toDomain(paymentRequestDto);
        var payment = processPaymentUseCase.process(paymentRequest);
        var paymentDto = paymentMapper.toDto(payment);
        var locationUri = LocationUri.fromRequest(paymentDto.getId());
        return ResponseEntity.created(locationUri).body(paymentDto);
    }

}
