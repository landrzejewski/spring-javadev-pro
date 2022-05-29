package pl.training.payments.adapters.rest;

import lombok.Data;
import pl.training.payments.adapters.commons.validation.Base;
import pl.training.payments.adapters.commons.validation.Extended;
import pl.training.payments.adapters.commons.validation.Money;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class PaymentRequestDto {

    @Min(value = 1, groups = {Base.class, Extended.class})
    private Long requestId;
    @Money(minValue = 10, groups = Extended.class)
    @Pattern(regexp = "\\d+ [A-Z]+", groups = Base.class)
    @NotNull(groups = Base.class)
    private String value;

}
