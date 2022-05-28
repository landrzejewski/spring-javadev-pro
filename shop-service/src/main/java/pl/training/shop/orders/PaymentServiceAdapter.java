package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.training.orders.ports.Payment;
import pl.training.orders.ports.PaymentService;
import pl.training.payments.ports.PaymentRequest;
import pl.training.payments.ports.ProcessPaymentUseCase;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static pl.training.commons.money.Currency.CURRENCY_SEPARATOR;

@Component
@RequiredArgsConstructor
public class PaymentServiceAdapter implements PaymentService {

    private final ProcessPaymentUseCase processPaymentUseCase;

    @Override
    public Optional<Payment> pay(Long requestId, BigDecimal value, String currency, Map<String, String> properties) {
        var paymentRequest = new PaymentRequest(requestId, value + CURRENCY_SEPARATOR + currency);
        var payment= processPaymentUseCase.process(paymentRequest);
        return Optional.of(new Payment(payment.getId(), payment.getStatus().name()));
    }

}
