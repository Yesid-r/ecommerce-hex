package co.edu.uptc.consumer;

import co.edu.uptc.model.order.gateways.CustomerGateway;
import co.edu.uptc.model.order.gateways.CustomerResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RestConsumer implements CustomerGateway {
    private final WebClient client;


    @Override
    public Mono<CustomerResponse> obtenerCliente(String customerId) {
        return client
                .get()
                .uri("/api/v1/customer/" + customerId)
                .retrieve()
                .bodyToMono(CustomerResponse.class);
    }
}
