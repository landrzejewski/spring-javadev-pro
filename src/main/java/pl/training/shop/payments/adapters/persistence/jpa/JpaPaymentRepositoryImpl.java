package pl.training.shop.payments.adapters.persistence.jpa;

import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

public class JpaPaymentRepositoryImpl implements JpaPaymentRepositoryExtension {

    @PersistenceContext
    @Setter
    private EntityManager entityManager;

    @Override
    public List<PaymentEntity> getWithValueGreaterThen(BigDecimal value) {
        return entityManager.createQuery("select p from Payment p where p.value >= :value", PaymentEntity.class)
                .setParameter("value", value)
                .getResultList();
    }

}
