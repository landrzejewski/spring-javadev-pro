package pl.training.orders.domain;

import lombok.Value;
import org.javamoney.moneta.FastMoney;

import java.util.List;

import static pl.training.commons.money.Currency.DEFAULT_CURRENCY;

@Value
class OrderDomain {

    Long id;
    List<OrderEntryDomain> entries;

    FastMoney getTotalValue() {
        return entries.stream()
                .map(entry -> entry.getPrice().multiply(entry.getQuantity()))
                .reduce(FastMoney.of(0, DEFAULT_CURRENCY), FastMoney::add);
    }

}
