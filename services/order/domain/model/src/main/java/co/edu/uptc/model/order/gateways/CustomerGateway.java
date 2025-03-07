package co.edu.uptc.model.order.gateways;

import reactor.core.publisher.Mono;

public interface CustomerGateway {
    Mono<Boolean> customerExists(String customerId);
}