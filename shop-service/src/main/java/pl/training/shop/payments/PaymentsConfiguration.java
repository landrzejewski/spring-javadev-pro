package pl.training.shop.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.payments.domain.DefaultPaymentServiceFactory;
import pl.training.payments.ports.*;

@Configuration
public class PaymentsConfiguration {

    private static final PaymentServiceFactory PAYMENT_SERVICE_FACTORY = new DefaultPaymentServiceFactory();

    @Bean
    public GetPaymentUseCase getPaymentUseCase(PaymentReader paymentReader) {
        return PAYMENT_SERVICE_FACTORY.create(paymentReader);
    }

    @Bean
    public ProcessPaymentUseCase processPaymentUseCase(PaymentWriter paymentWriter, TimeProvider timeProvider) {
        return PAYMENT_SERVICE_FACTORY.create(paymentWriter, timeProvider);
    }

}
