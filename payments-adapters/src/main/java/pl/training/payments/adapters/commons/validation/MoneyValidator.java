package pl.training.payments.adapters.commons.validation;

import org.javamoney.moneta.FastMoney;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MoneyValidator implements ConstraintValidator<Money, String> {

    private double minValue;

    @Override
    public void initialize(Money money) {
        minValue = money.minValue();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        try {
            result = FastMoney.parse(value).isGreaterThanOrEqualTo(minValue);
        } catch (Exception exception) {
            result = false;
        }
        return result;
    }

}
