package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public class ConsolePaymentLogger {

    private static final String LOG_FORMAT = "A new payment of %s has been initiated";

    public void log(Payment payment) {
        log.info(createLogEntry(payment));
    }

    private String createLogEntry(Payment payment) {
        return LOG_FORMAT.formatted(payment.getValue());
    }

    public void init() {
        log.info("Initializing payment logger");
    }

    public void destroy() {
        log.info("Destroying payment logger");
    }

}
