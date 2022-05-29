package pl.training.shop.commons.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ExceptionResponseBuilder responseBuilder;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> onException(Exception exception, Locale locale) {
        return responseBuilder.build(exception, INTERNAL_SERVER_ERROR, locale);
    }

}
