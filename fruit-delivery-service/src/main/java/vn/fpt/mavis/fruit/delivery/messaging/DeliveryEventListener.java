package vn.fpt.mavis.fruit.delivery.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class DeliveryEventListener {

    private final RestTemplate restTemplate;

    public DeliveryEventListener(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RabbitListener(queues = "fruit.order.events")
    public void handleOrderConfirmedEvent(Map<String, Object> eventPayload) {
        Object orderId = eventPayload.getOrDefault("orderId", 1L);
        try {
            restTemplate.put(
                    "http://fruit-order-service:8081/api/orders/{orderId}/delivery-status",
                    Map.of("deliveryStatus", "DELIVERED"),
                    orderId
            );
        } catch (Exception ignored) {
            // Fallback khi chay don le
        }
    }
}
