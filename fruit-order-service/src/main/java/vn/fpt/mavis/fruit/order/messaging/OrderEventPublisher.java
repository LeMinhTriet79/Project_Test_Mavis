package vn.fpt.mavis.fruit.order.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public OrderEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrderConfirmedEvent(Long orderId, String fruitCode, Integer quantityKg) {
        Map<String, Object> payload = Map.of(
                "orderId", orderId,
                "fruitCode", fruitCode,
                "quantityKg", quantityKg,
                "eventType", "FRUIT_ORDER_CONFIRMED"
        );
        rabbitTemplate.convertAndSend("fruit.order.events", payload);
    }
}
