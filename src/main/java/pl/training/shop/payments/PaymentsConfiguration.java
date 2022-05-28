package pl.training.shop.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.shop.time.TimeProvider;

@Configuration
public class PaymentsConfiguration {

    @Bean
    public PaymentIdGenerator paymentIdGenerator() {
        return new UuidPaymentIdGenerator();
    }

    @Bean
    public PaymentFeeCalculator paymentFeeCalculator() {
        return new PercentagePaymentFeeCalculator(0.01);
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new JpaPaymentRepository();
    }

    @Bean
    public PaymentService paymentService(PaymentIdGenerator paymentIdGenerator, PaymentFeeCalculator paymentFeeCalculator, PaymentRepository paymentRepository, TimeProvider timeProvider) {
        return new PaymentProcessor(paymentIdGenerator, paymentFeeCalculator, paymentRepository, timeProvider);
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ConsolePaymentLogger paymentLogger() {
        return new ConsolePaymentLogger();
    }

}
