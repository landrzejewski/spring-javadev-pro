package pl.training.shop.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.training.shop.commons.web.ExceptionDto;
import pl.training.shop.commons.web.ExceptionResponseBuilder;
import pl.training.shop.payments.domain.PaymentNotFoundException;

import java.util.Locale;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice("pl.training.shop.payments.adapters.rest")
@RequiredArgsConstructor
public class PaymentRestExceptionHandler {

    private final ExceptionResponseBuilder responseBuilder;

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionDto> onPaymentNotFound(PaymentNotFoundException exception, Locale locale) {
        return responseBuilder.build(exception, NOT_FOUND, locale);
    }

}
