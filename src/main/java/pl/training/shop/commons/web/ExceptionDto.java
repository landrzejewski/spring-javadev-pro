package pl.training.shop.commons.web;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ExceptionDto {

    LocalDateTime timestamp = LocalDateTime.now();
    String description;

}
