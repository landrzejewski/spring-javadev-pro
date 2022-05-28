package pl.training.shop.payments.domain;

import java.util.UUID;

class UuidPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }

}
