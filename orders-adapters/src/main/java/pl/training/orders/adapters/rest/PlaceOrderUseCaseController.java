package pl.training.orders.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.training.orders.ports.PlaceOrderUseCase;

@RequestMapping("api/orders")
@RestController
@RequiredArgsConstructor
public class PlaceOrderUseCaseController {

    private final PlaceOrderUseCase placeOrderUseCase;
    private final RestOrderMapper mapper;

    @PostMapping
    public ResponseEntity<Void> place(@RequestBody OrderDto orderDto) {
        var order = mapper.toPorts(orderDto);
        placeOrderUseCase.place(order);
        return ResponseEntity.accepted().build();
    }

}
