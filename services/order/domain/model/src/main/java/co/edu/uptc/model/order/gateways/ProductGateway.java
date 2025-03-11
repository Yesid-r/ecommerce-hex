package co.edu.uptc.model.order.gateways;

import co.edu.uptc.model.order.dto.ProductPurchaseResponseDTO;
import co.edu.uptc.model.order.dto.ProductRequestDTO;
import co.edu.uptc.model.orderline.OrderLine;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductGateway {
    Mono<List<ProductPurchaseResponseDTO>> getProductsForOrder(List<ProductRequestDTO> productIds);
}