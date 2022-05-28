package pl.training.payments.adapters.commons.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MoneyValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Money {

    String message() default "invalid money";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    double minValue() default 1;

}
