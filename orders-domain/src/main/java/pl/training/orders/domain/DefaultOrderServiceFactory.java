package pl.training.orders.domain;

import org.mapstruct.factory.Mappers;
import pl.training.orders.ports.OrderServiceFactory;
import pl.training.orders.ports.PaymentService;
import pl.training.orders.ports.PlaceOrderUseCase;
import pl.training.orders.ports.ProductsProvider;

public class DefaultOrderServiceFactory implements OrderServiceFactory {

    private static final OrderDomainMapper MAPPER = Mappers.getMapper(OrderDomainMapper.class);

    @Override
    public PlaceOrderUseCase create(PaymentService paymentService, ProductsProvider productsProvider) {
        var placeOrderService = new PlaceOrderService(paymentService);
        MAPPER.setProductsProvider(productsProvider);
        return new PlaceOrderUseCaseAdapter(placeOrderService, MAPPER);
    }

}
