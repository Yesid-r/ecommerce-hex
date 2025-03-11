package co.edu.uptc.consumer;

import co.edu.uptc.model.order.dto.ProductPurchaseResponseDTO;
import co.edu.uptc.model.order.dto.ProductRequestDTO;
import co.edu.uptc.model.order.gateways.ProductGateway;
import co.edu.uptc.model.orderline.OrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestConsumerProduct implements ProductGateway {
    private final WebClient client;



    @Override
    public Mono<List<ProductPurchaseResponseDTO>> getProductsForOrder(List<ProductRequestDTO> productsRequest) {
        //TODO implementar metodo post para la siguiente ruta: http://localhost:8080/api/v1/producto/purchase

        return client.post()
                .uri("/api/v1/producto/purchase")
                .bodyValue(productsRequest)
                .retrieve()
                .bodyToFlux(ProductPurchaseResponseDTO.class)
                .collectList();
    }
}
