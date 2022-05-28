package pl.training.orders.ports;

import lombok.Value;

@Value
public class Product {

    Long id;
    String name;
    String price;

}
