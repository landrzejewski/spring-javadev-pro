package pl.training.payments.adapters.commons.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalRestExceptionHandler {

    private static final String KEY_VALUE_SEPARATOR = " ";
    private static final String DELIMITER = ", ";

    private final RestExceptionResponseBuilder responseBuilder;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> onException(Exception exception, Locale locale) {
        return responseBuilder.build(exception, INTERNAL_SERVER_ERROR, locale);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> onMethodArgumentNotValidException(MethodArgumentNotValidException exception, Locale locale) {
        var description = responseBuilder.getDescription(exception, locale, getValidationErrors(exception));
        return responseBuilder.build(description, BAD_REQUEST);
    }

    private String getValidationErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + KEY_VALUE_SEPARATOR + fieldError.getDefaultMessage())
                .collect(joining(DELIMITER));
    }

}
