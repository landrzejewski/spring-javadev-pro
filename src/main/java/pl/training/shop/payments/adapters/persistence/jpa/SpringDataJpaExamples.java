package pl.training.shop.payments.adapters.persistence.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import static pl.training.shop.payments.adapters.persistence.jpa.SearchCriteria.Operator.EQUAL;
import static pl.training.shop.payments.adapters.persistence.jpa.SearchCriteria.Operator.MATCH_START;

@Transactional
@Component
@Log
@RequiredArgsConstructor
public class SpringDataJpaExamples {

    private final JpaPaymentRepository jpaPaymentRepository;

    @PostConstruct
    public void onStart() {
        var payment = new PaymentEntity();
        payment.setId(UUID.randomUUID().toString());
        payment.setStatus("STARTED");
        payment.setTimestamp(Instant.now());
        payment.setValue(BigDecimal.valueOf(1_000));
        payment.setCurrency("PLN");
        // jpaPaymentRepository.save(payment);

        var paymentExample = new PaymentEntity();
        paymentExample.setStatus("STARTED");
        var matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues();
        //var result = jpaPaymentRepository.findAll(Example.of(paymentExample, matcher));

        var criteria = Set.of(
          new SearchCriteria("status", "STAR", MATCH_START),
          new SearchCriteria("value", BigDecimal.valueOf(1_000), EQUAL)
        );
        var result = jpaPaymentRepository.findAll(new PaymentSpecification(criteria), PageRequest.of(0, 10));

        result.forEach(paymentEntity -> log.info(paymentEntity.toString()));
    }

}
