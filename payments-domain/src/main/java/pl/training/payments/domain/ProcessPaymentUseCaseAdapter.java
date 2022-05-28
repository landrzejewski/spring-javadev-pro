package pl.training.payments.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.training.payments.ports.Payment;
import pl.training.payments.ports.PaymentRequest;
import pl.training.payments.ports.ProcessPaymentUseCase;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ProcessPaymentUseCaseAdapter implements ProcessPaymentUseCase {

    private final ProcessPaymentService processPaymentService;
    private final PaymentDomainMapper mapper;

    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var paymentRequestDomain = mapper.toDomain(paymentRequest);
        var payment = processPaymentService.process(paymentRequestDomain);
        return mapper.toPorts(payment);
    }

}
