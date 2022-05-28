package pl.training.shop.orders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.orders.domain.DefaultOrderServiceFactory;
import pl.training.orders.ports.OrderServiceFactory;
import pl.training.orders.ports.PaymentService;
import pl.training.orders.ports.PlaceOrderUseCase;
import pl.training.orders.ports.ProductsProvider;

@Configuration
public class OrdersConfiguration {

    private static final OrderServiceFactory ORDER_SERVICE_FACTORY = new DefaultOrderServiceFactory();

    @Bean
    public PlaceOrderUseCase placeOrderUseCase(PaymentService paymentService, ProductsProvider productsProvider) {
        return ORDER_SERVICE_FACTORY.create(paymentService, productsProvider);
    }

}
