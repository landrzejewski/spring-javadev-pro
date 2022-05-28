package pl.training.shop.payments.adapters.persistence.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.domain.PaymentStatus;
import pl.training.shop.payments.ports.PaymentRepository;

import java.util.Optional;

@Transactional // (propagation = Propagation.MANDATORY)
@Component
@RequiredArgsConstructor
public class JpaPaymentRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentRepository paymentRepository;
    private final JpaPersistencePaymentMapper paymentMapper;

    @Override
    public Payment save(Payment payment) {
        var entity = paymentMapper.toEntity(payment);
        paymentRepository.save(entity);
        return paymentMapper.toDomain(entity);
    }

    @Override
    public Optional<Payment> getById(String id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toDomain);
    }

    @Override
    public ResultPage<Payment> getByStatus(PaymentStatus status, Page page) {
        var pageRequest = PageRequest.of(page.getNumber(), page.getSize());
        var resultPage = paymentRepository.getByStatus(status.name(), pageRequest);
        var data = paymentMapper.toDomain(resultPage.getContent());
        return new ResultPage<>(data, page.getNumber(), resultPage.getTotalPages());
    }

}
