package pl.training.shop.payments;

import lombok.*;
import org.javamoney.moneta.FastMoney;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    private String id;
    private FastMoney value;
    private Instant timestamp;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

}
