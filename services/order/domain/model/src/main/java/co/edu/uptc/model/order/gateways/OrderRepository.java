package co.edu.uptc.model.order.gateways;

import co.edu.uptc.model.order.Order;
import reactor.core.publisher.Mono;

public interface OrderRepository {
    Mono<Order> saveOrder(Order order);
}
