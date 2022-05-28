package pl.training.payments.ports;

import lombok.Value;

@Value
public class PaymentRequest {

    Long id;
    String value;

}
