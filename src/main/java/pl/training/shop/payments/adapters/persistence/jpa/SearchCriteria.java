package pl.training.shop.payments.adapters.persistence.jpa;

import lombok.Value;

@Value
public class SearchCriteria {

    String property;
    Object value;
    Operator operator;

    public enum Operator {

        EQUAL,
        NOT_EQUAL,
        MATCH,
        MATCH_START,
        IN,
        GREATER_THEN

    }

}
