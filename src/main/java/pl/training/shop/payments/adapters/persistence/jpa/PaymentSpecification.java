package pl.training.shop.payments.adapters.persistence.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Set;

@RequiredArgsConstructor
public class PaymentSpecification implements Specification<PaymentEntity> {

    private final Set<SearchCriteria> searchCriteria;

    @Override
    public Predicate toPredicate(Root<PaymentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        var predicates = searchCriteria.stream()
                .map(criteria -> map(criteria, root, criteriaBuilder))
                .toArray(Predicate[]::new);
        return criteriaBuilder.and(predicates);
    }

    private Predicate map(SearchCriteria searchCriteria, Root<PaymentEntity> root, CriteriaBuilder builder) {
        var property = searchCriteria.getProperty();
        var value = searchCriteria.getValue();
        return switch (searchCriteria.getOperator()) {
            case GREATER_THEN -> builder.greaterThan(root.get(property), value.toString());
            case EQUAL -> builder.equal(root.get(property), value.toString());
            case NOT_EQUAL -> builder.notEqual(root.get(property), value.toString());
            case MATCH -> builder.like(root.get(property), "%" + value.toString() + "%");
            case MATCH_START -> builder.like(root.get(property), value.toString() + "%");
            default -> throw new IllegalArgumentException();
        };
    }

}
