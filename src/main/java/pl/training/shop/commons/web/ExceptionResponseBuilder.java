package pl.training.shop.commons.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ExceptionResponseBuilder {

    private static final String UNKNOWN_EXCEPTION = "Unknown exception";

    private final MessageSource messageSource;

    public ResponseEntity<ExceptionDto> build(Exception exception, HttpStatus status, Locale locale) {
        var description = getDescription(exception, locale);
        return build(description, status);
    }

    public ResponseEntity<ExceptionDto> build(String description, HttpStatus status) {
        return ResponseEntity.status(status).body(new ExceptionDto(description));
    }

    public String getDescription(Exception exception, Locale locale, String...parameters) {
        var key = exception.getClass().getSimpleName();
        String description;
        try {
            description = messageSource.getMessage(key, parameters, locale);
        } catch (NoSuchMessageException ex) {
            description = UNKNOWN_EXCEPTION;
        }
        return description;
    }

}
