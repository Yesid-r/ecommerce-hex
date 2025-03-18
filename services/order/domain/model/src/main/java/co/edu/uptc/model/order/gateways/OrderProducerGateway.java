package co.edu.uptc.model.order.gateways;


import co.edu.uptc.model.order.OrderConfirmation;

public interface OrderProducerGateway {
    void sendOrder(OrderConfirmation order);
}
