package pl.training.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.training.payments.adapters.commons.web.ExceptionDto;
import pl.training.payments.adapters.commons.web.RestExceptionResponseBuilder;
import pl.training.payments.ports.PaymentNotFoundException;

import java.util.Locale;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(HIGHEST_PRECEDENCE)
@ControllerAdvice("pl.training.shop.payments.adapters.rest")
@RequiredArgsConstructor
public class PaymentRestExceptionHandler {

    private final RestExceptionResponseBuilder responseBuilder;

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionDto> onPaymentNotFound(PaymentNotFoundException exception, Locale locale) {
        return responseBuilder.build(exception, NOT_FOUND, locale);
    }

}
