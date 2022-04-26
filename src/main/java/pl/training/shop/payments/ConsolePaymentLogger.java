package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Order(100)
@Aspect
@Log
@RequiredArgsConstructor
public class ConsolePaymentLogger {

    @Pointcut("execution(pl.training.shop.payments.Payment pl.training.shop.*.PaymentProcessor.proc*(..))")
    // @Pointcut("@annotation(LogPayment)")
    // @Pointcut("bean(paymentService)")
    void process() {
    }

    @Before(value = "process() && args(paymentRequest)")
    public void onStart(JoinPoint joinPoint, PaymentRequest paymentRequest) {
        // joinPoint.getSignature();
        log.info("New payment request: " + paymentRequest);
    }

    @AfterReturning(value = "process()", returning = "payment")
    public void onSuccess(Payment payment) {
        log.info("A new payment of %s has been created".formatted(payment.getValue()));
    }

    @AfterThrowing(value = "process()", throwing = "exception")
    public void onFailure(JoinPoint joinPoint, RuntimeException exception) {
        log.info("Payment processing failed: %s (method: %s)".formatted(exception.getClass().getSimpleName(), joinPoint.getSignature().getName()));
    }

    @After("process()")
    public void onFinish() {
        log.info("Payment processing complete");
    }

    public void init() {
        log.info("Initializing payment logger");
    }

    public void destroy() {
        log.info("Destroying payment logger");
    }

}
