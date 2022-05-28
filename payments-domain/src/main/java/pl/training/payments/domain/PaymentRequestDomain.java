package pl.training.payments.domain;

import lombok.Value;
import org.javamoney.moneta.FastMoney;

@Value
class PaymentRequestDomain {

    Long id;
    FastMoney value;

}
