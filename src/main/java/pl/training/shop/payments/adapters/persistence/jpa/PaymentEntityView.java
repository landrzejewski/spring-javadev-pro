package pl.training.shop.payments.adapters.persistence.jpa;

import lombok.Value;

@Value
public class PaymentEntityView {

    String id;
    String status;

}
