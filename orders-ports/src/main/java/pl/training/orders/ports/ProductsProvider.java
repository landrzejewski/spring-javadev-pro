package pl.training.orders.ports;

import java.util.Optional;

public interface ProductsProvider {

    Optional<Product> getById(Long id);

}
