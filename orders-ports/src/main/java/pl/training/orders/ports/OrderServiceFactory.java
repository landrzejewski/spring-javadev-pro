package pl.training.orders.ports;

public interface OrderServiceFactory {

    PlaceOrderUseCase create(PaymentService paymentService, ProductsProvider productsProvider);

}
