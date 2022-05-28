package pl.training.shop.payments.adapters.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;
import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, String>, JpaPaymentRepositoryExtension, JpaSpecificationExecutor<PaymentEntity> {

    //@EntityGraph(value = "WITH_PROPERTIES", type = FETCH)
    //@EntityGraph(attributePaths = { "properties" })
    @Lock(PESSIMISTIC_WRITE)
    Page<PaymentEntity> getByStatus(String status, Pageable pageable);

    @Query("select p from Payment p where p.status = 'COMPLETED' and p.value >= :value")
    List<PaymentEntity> getCompletedWithValue(@Param("value") BigDecimal value);

    @Query("select new pl.training.shop.payments.adapters.persistence.jpa.PaymentEntityView(p.id, p.status) from Payment p where p.id = :id")
    Optional<PaymentEntityView> getViewById(String id);

    @Query("select p.id as id, p.status as status from Payment p where p.id = :id")
    Optional<PaymentEntityDescription> getDescriptionById(String id);

}
