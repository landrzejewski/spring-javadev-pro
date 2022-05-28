package pl.training.payments.ports;

import lombok.Value;

import java.time.Instant;

@Value
public class Payment {

    String id;
    String value;
    Instant timestamp;
    PaymentStatus status;

}
