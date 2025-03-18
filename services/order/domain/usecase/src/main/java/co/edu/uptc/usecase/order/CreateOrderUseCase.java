package co.edu.uptc.usecase.order;

import co.edu.uptc.model.order.Order;
import co.edu.uptc.model.order.OrderConfirmation;
import co.edu.uptc.model.order.dto.CustomerDTO;
import co.edu.uptc.model.order.dto.PaymentMethod;
import co.edu.uptc.model.order.dto.ProductPurchaseResponseDTO;
import co.edu.uptc.model.order.dto.ProductRequestDTO;
import co.edu.uptc.model.order.gateways.CustomerGateway;
import co.edu.uptc.model.order.gateways.OrderProducerGateway;
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
    private final OrderProducerGateway orderProducerGateway;


    public Mono<Order> createOrder(String customerId, List<ProductRequestDTO> products) {
        return customerGateway.obtenerCliente(customerId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Customer not found: " + customerId)))
                .flatMap(customer ->
                        productGateway.getProductsForOrder(products)
                                .flatMap(purchaseProduct -> {
                                    if (purchaseProduct == null || purchaseProduct.isEmpty()) {
                                        return Mono.error(new IllegalArgumentException("Product not found"));
                                    }
                                    return Mono.just(purchaseProduct);
                                })
                                .map(purchaseProduct -> {
                                    Order order = buildOrder(customerId, createOrderLines(purchaseProduct));
                                    //return orderRepository.save(order)
                                      //      .doOnSuccess(savedOrder ->
                                        //            sendOrderCreatedEvent(savedOrder));
                                    //TODO mappear order completa
                                    CustomerDTO customerDTO = new CustomerDTO(customer.id(), customer.name(), customer.lastName(), customer.email());
                                    OrderConfirmation orderConfirmation = new OrderConfirmation(order.getId(), order.getTotalAmount(), PaymentMethod.CREDIT_CARD, customerDTO, purchaseProduct);
                                    System.out.println("orderConfirmation.toString() = " + orderConfirmation.toString());
                                    orderProducerGateway.sendOrder(orderConfirmation);

                                    orderRepository.saveOrder(order);
                                    return order;

                                })
                );
    }



    public List<OrderLine> createOrderLines(List<ProductPurchaseResponseDTO> products){
        List<OrderLine> orderLines = products.stream()
                .map(product -> OrderLine.builder()
                        .productId(product.id())
                        .nameProduct(product.nombre())
                        .quantity(product.cantidad())
                        .price(BigDecimal.valueOf(product.precio()))
                        .build())
                .collect(Collectors.toList());
        return orderLines;


    }
    private Order buildOrder(String customerId, List<OrderLine> orderLines) {
        Order order = new Order();
        order.setId(String.valueOf(UUID.randomUUID()));
        order.setCustomerId(customerId);
        order.setOrderLines(orderLines);
        order.setTotalAmount(calculateTotalAmount(orderLines));
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("CREATED");
        return order;
    }

    private BigDecimal calculateTotalAmount(List<OrderLine> orderLines) {
        return orderLines.stream()
                .map(line -> line.getPrice().multiply(new BigDecimal(line.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
