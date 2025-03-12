package co.edu.uptc.usecase.order;

import co.edu.uptc.model.order.Order;
import co.edu.uptc.model.order.dto.ProductRequestDTO;
import co.edu.uptc.model.order.gateways.CustomerGateway;
import co.edu.uptc.model.order.gateways.OrderRepository;
import co.edu.uptc.model.order.gateways.ProductGateway;
import co.edu.uptc.model.orderline.OrderLine;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final CustomerGateway customerGateway;
    private final ProductGateway productGateway;

    public Mono<Order> createOrder(String customerId, List<ProductRequestDTO> products) {
        return customerGateway.obtenerCliente(customerId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Customer not found: " + customerId))) // Si el cliente no existe, retorna error.
                .flatMap(customer ->
                        productGateway.getProductsForOrder(products)
                                .flatMap(purchaseProduct -> {
                                    if (purchaseProduct == null || purchaseProduct.isEmpty()) {
                                        System.out.println("purchaseProduct = " + purchaseProduct);
                                        return Mono.error(new IllegalArgumentException("Product not found"));
                                    }
                                    return Mono.just(purchaseProduct);
                                })
                                .map(purchaseProduct -> {
                                    // TODO: Implementar la lógica de pago y creación de la orden.
                                    Order order = new Order();
                                    order.setCustomerId(customerId);
                                    return order;
                                })
                );
    }


    private Order buildOrder(String customerId, List<OrderLine> orderLines) {
        BigDecimal totalAmount = calculateTotalAmount(orderLines);
        LocalDateTime now = LocalDateTime.now();
        
        return Order.builder()
                .id(UUID.randomUUID().toString())
                .reference(generateOrderReference())
                .totalAmount(totalAmount)
                .customerId(customerId)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    private BigDecimal calculateTotalAmount(List<OrderLine> orderLines) {
        // Calculate the total amount based on the order lines
        // This is a placeholder implementation
        return BigDecimal.ZERO;
    }

    private String generateOrderReference() {
        // Generate a unique order reference
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private List<OrderLine> updateOrderLinesWithOrder(List<OrderLine> orderLines, Order order) {
        return orderLines.stream()
                .map(ol -> OrderLine.builder()
                        .id(ol.getId())
                        .order(order)
                        .productId(ol.getProductId())
                        .skuProduct(ol.getSkuProduct())
                        .build())
                .collect(Collectors.toList());
    }
}