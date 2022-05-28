package pl.training.orders.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.training.orders.ports.Order;
import pl.training.orders.ports.PlaceOrderUseCase;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class PlaceOrderUseCaseAdapter implements PlaceOrderUseCase {

    private final PlaceOrderService placeOrderService;
    private final OrderDomainMapper mapper;

    @Override
    public void place(Order order) {
        var orderDomain = mapper.toDomain(order);
        placeOrderService.place(orderDomain);
    }

}
