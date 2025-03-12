package co.edu.uptc.consumer;

import co.edu.uptc.model.order.dto.ProductPurchaseResponseDTO;
import co.edu.uptc.model.order.dto.ProductRequestDTO;
import co.edu.uptc.model.order.gateways.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestConsumerProduct implements ProductGateway {
    private final WebClient client;



    @Override
    public Mono<List<ProductPurchaseResponseDTO>> getProductsForOrder(List<ProductRequestDTO> productsRequest) {


        return client.post()
                .uri("/api/v1/producto/purchase")
                .bodyValue(productsRequest)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToFlux(ProductPurchaseResponseDTO.class).collectList();
                    } else {
                        return response.createException().flatMap(Mono::error);
                    }
                });


    }
}
