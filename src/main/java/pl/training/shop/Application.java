package pl.training.shop;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.shop.commons.Page;
import pl.training.shop.payments.domain.PaymentRequest;
import pl.training.shop.payments.domain.PaymentStatus;
import pl.training.shop.payments.ports.PaymentService;

@SpringBootApplication
@Log
@RequiredArgsConstructor
public class Application implements ApplicationRunner {

    private static final String DEFAULT_CURRENCY_CODE = "PLN";

    private final PaymentService paymentService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var paymentRequest = new PaymentRequest(1L, FastMoney.of(1_000, DEFAULT_CURRENCY_CODE));
        paymentService.process(paymentRequest);
        var payments = paymentService.getByStatus(PaymentStatus.STARTED, new Page(0,10));
        log.info(payments.toString());
    }

}
