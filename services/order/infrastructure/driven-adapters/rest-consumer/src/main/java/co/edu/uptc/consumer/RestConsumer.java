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
        System.out.println("customerId = " + customerId);
        return client
                .get()
                .uri("/api/v1/customer/" + customerId)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        System.out.println("Customer found");
                        System.out.println("response = " + response.toString());
                        return response.bodyToMono(CustomerResponse.class);
                    } else {
                        System.out.println("ERROR ->>> Not customer Found");
                        return response.createException().flatMap(Mono::error);
                    }
                });

    }
}
