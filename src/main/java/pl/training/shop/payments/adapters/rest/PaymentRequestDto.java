package pl.training.shop.payments.adapters.rest;

import lombok.Data;

@Data
public class PaymentRequestDto {

    private Long requestId;
    private String value;

}
