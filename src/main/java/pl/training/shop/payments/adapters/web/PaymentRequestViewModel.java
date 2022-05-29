package pl.training.shop.payments.adapters.web;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class PaymentRequestViewModel {

    @Pattern(regexp = "\\d+ PLN")
    @NotEmpty
    private String value;

}
