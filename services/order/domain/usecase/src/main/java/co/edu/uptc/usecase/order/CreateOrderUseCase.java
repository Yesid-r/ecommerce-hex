package co.edu.uptc.usecase.order;

import co.edu.uptc.model.order.Order;
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
    //private final ProductGateway productGateway;

    public Mono<Order> createOrder(String customerId, List<Integer> productIds) {
        var customer = customerGateway.obtenerCliente(customerId);
        if (customer == null) {
            return Mono.error(new IllegalArgumentException("Customer not found: " + customerId));
        }
        //var purchaseProduct = productGateway.getProductsForOrder(productIds);

        //TODO implementar payment y notification
//        return customerGateway.customerExists(customerId)
//                .flatMap(exists -> {
//                    if (!exists) {
//                        return Mono.error(new IllegalArgumentException("Customer not found: " + customerId));
//                    }
//
//                    return productGateway.getProductsForOrder(productIds)
//                            .flatMap(orderLines -> {
//                                Order order = buildOrder(customerId, orderLines);
//                                return orderRepository.saveOrder(order)
//                                        .flatMap(savedOrder -> {
//                                            // Update the order reference in order lines and save them
//                                            List<OrderLine> updatedOrderLines = updateOrderLinesWithOrder(orderLines, savedOrder);
//                                            // TODO: Save the order lines using a repository
//                                            return Mono.just(savedOrder);
//                                        });
//                            });
//                });
        return orderRepository.saveOrder(Order.builder().customerId(customerId).totalAmount(BigDecimal.TEN).build());


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