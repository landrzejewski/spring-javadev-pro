package pl.training.payments.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.training.payments.ports.Page;
import pl.training.payments.ports.PaymentNotFoundException;
import pl.training.payments.ports.ResultPage;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class GetPaymentService {

    private final PaymentReaderAdapter paymentReader;

    PaymentDomain getById(String id) {
        return paymentReader.getById(id)
                .orElseThrow(PaymentNotFoundException::new);
    }

    ResultPage<PaymentDomain> getByStatus(PaymentStatusDomain status, Page page) {
        return paymentReader.getByStatus(status, page);
    }

}
