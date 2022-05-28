package pl.training.payments.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class PercentagePaymentFeeCalculator implements PaymentFeeCalculator {

    private final double percentage;

    @Override
    public FastMoney calculateFee(FastMoney paymentValue) {
        return paymentValue.multiply(percentage);
    }

}
