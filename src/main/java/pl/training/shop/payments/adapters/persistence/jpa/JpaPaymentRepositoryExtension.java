package pl.training.shop.payments.adapters.persistence.jpa;

import java.math.BigDecimal;
import java.util.List;

public interface JpaPaymentRepositoryExtension {

    List<PaymentEntity> getWithValueGreaterThen(BigDecimal value);

}
