package pl.training.payments.adapters.commons.web;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ExceptionDto {

    LocalDateTime timestamp = LocalDateTime.now();
    String description;

}
