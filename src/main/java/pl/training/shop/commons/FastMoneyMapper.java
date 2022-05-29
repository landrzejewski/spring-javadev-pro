package pl.training.shop.commons;

import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FastMoneyMapper {

    default String toText(FastMoney money) {
        return money != null ? money.toString() : "";
    }

    default FastMoney toMoney(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }
        return FastMoney.parse(text);
    }

}
