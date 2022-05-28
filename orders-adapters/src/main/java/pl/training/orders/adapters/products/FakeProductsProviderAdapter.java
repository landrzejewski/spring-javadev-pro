package pl.training.orders.adapters.products;

import org.springframework.stereotype.Component;
import pl.training.orders.ports.Product;
import pl.training.orders.ports.ProductsProvider;

import java.util.Optional;

@Component
public class FakeProductsProviderAdapter implements ProductsProvider {

    @Override
    public Optional<Product> getById(Long id) {
        return Optional.of(new Product(1L, "Fake product", "200 PLN"));
    }

}
