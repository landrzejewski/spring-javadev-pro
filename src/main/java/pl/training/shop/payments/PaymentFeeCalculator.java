package pl.training.shop.payments;

import org.javamoney.moneta.FastMoney;

public interface PaymentFeeCalculator {

    FastMoney calculateFee(FastMoney paymentValue);

}
