package pl.training.payments.adapters.persistence.jpa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.math.BigDecimal;
import java.time.Instant;

@NamedQuery(name = PaymentEntity.GET_BY_STATUS, query = "select p from Payment p where p.status = :status")
@NamedQuery(name = PaymentEntity.COUNT_BY_STATUS, query = "select count(p) from Payment p where p.status = :status")
@Entity(name = "Payment")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class PaymentEntity {

    public static final String GET_BY_STATUS = "paymentsByStatus";
    public static final String COUNT_BY_STATUS = "paymentsCountByStatus";

    @Id
    private String id;
    @Column(name = "amount")
    private BigDecimal value;
    private String currency;
    private Instant timestamp;
    private String status;

}
