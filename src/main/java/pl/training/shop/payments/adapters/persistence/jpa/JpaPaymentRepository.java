package pl.training.shop.payments.adapters.persistence.jpa;

import lombok.Setter;
import org.springframework.stereotype.Repository;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class JpaPaymentRepository {

    @PersistenceContext
    @Setter
    private EntityManager entityManager;

    public PaymentEntity save(PaymentEntity payment) {
        entityManager.persist(payment);
        return payment;
    }

    public Optional<PaymentEntity> getById(String id) {
        return Optional.ofNullable(entityManager.find(PaymentEntity.class, id));
    }

    public ResultPage<PaymentEntity> getByStatus(String status, Page page) {
        var payments = entityManager.createNamedQuery(PaymentEntity.GET_BY_STATUS, PaymentEntity.class)
                .setParameter("status", status)
                .setFirstResult(page.getOffset())
                .setMaxResults(page.getSize())
                .getResultList();
        var count = entityManager.createNamedQuery(PaymentEntity.COUNT_BY_STATUS, Long.class)
                .setParameter("status", status)
                .getSingleResult();
        var totalPages = (count / page.getSize()) + 1;
        return new ResultPage<>(payments, page.getNumber(), totalPages);
    }

}
