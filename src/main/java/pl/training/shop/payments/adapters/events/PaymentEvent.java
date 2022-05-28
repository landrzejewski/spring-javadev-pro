package pl.training.shop.payments.adapters.events;

import lombok.Value;
import pl.training.shop.payments.domain.Payment;

@Value
public class PaymentEvent {

    Payment payment;
    PaymentEventType type;

    enum PaymentEventType {

        CREATED, FAILED

    }

}
