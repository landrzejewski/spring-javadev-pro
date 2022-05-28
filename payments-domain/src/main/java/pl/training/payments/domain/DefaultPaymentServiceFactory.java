package pl.training.payments.domain;

import org.mapstruct.factory.Mappers;
import pl.training.payments.ports.*;

public class DefaultPaymentServiceFactory implements PaymentServiceFactory {

    private static final PaymentDomainMapper MAPPER = Mappers.getMapper(PaymentDomainMapper.class);
    private static final PaymentIdGenerator PAYMENT_ID_GENERATOR = new UuidPaymentIdGenerator();
    private static final PaymentFeeCalculator PAYMENT_FEE_CALCULATOR = new PercentagePaymentFeeCalculator(0.01);

    @Override
    public GetPaymentUseCase create(PaymentReader paymentReader) {
        var paymentReaderAdapter = new PaymentReaderAdapter(paymentReader, MAPPER);
        var getPaymentService = new GetPaymentService(paymentReaderAdapter);
        return new GetPaymentUseCaseAdapter(getPaymentService, MAPPER);
    }

    @Override
    public ProcessPaymentUseCase create(PaymentWriter paymentWriter, TimeProvider timeProvider) {
        var paymentWriterAdapter = new PaymentWriterAdapter(paymentWriter, MAPPER);
        var processPaymentService = new ProcessPaymentService(PAYMENT_ID_GENERATOR, PAYMENT_FEE_CALCULATOR, paymentWriterAdapter, timeProvider);
        return new ProcessPaymentUseCaseAdapter(processPaymentService, MAPPER);
    }

}
