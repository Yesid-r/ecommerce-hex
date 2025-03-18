package co.edu.uptc.kafka;

import co.edu.uptc.model.order.Order;
import co.edu.uptc.model.order.OrderConfirmation;
import co.edu.uptc.model.order.gateways.OrderProducerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class OrderProducer implements OrderProducerGateway {

    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;


    @Override
    public void sendOrder(OrderConfirmation orderConfirmation) {
        System.out.println("orderConfirmation = " + orderConfirmation);
        Message<OrderConfirmation> message = MessageBuilder.withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC,"order-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
