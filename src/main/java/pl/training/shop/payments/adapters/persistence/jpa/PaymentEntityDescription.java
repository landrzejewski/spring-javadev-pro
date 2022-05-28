package pl.training.shop.payments.adapters.persistence.jpa;

import org.springframework.beans.factory.annotation.Value;

public interface PaymentEntityDescription {

    String getId();
    String getStatus();
    @Value("#{target.id + ' ' + target.status}")
    String getDescription();

}
