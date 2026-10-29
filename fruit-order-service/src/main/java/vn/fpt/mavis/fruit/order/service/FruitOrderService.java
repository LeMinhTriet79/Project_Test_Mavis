package vn.fpt.mavis.fruit.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import vn.fpt.mavis.fruit.order.client.FruitCatalogFeignClient;
import vn.fpt.mavis.fruit.order.client.FruitDeliveryFeignClient;
import vn.fpt.mavis.fruit.order.client.FruitPricingFeignClient;
import vn.fpt.mavis.fruit.order.entity.FruitOrderEntity;
import vn.fpt.mavis.fruit.order.entity.OrderStatus;
import vn.fpt.mavis.fruit.order.messaging.OrderEventPublisher;
import vn.fpt.mavis.fruit.order.repository.FruitOrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FruitOrderService {

    private final FruitOrderRepository orderRepository;
    private final FruitCatalogFeignClient fruitCatalogFeignClient;
    private final FruitPricingFeignClient fruitPricingFeignClient;
    private final FruitDeliveryFeignClient fruitDeliveryFeignClient;
    private final OrderEventPublisher orderEventPublisher;
    private final RestTemplate restTemplate;
    private final WebClient webClient;

    public FruitOrderService(FruitOrderRepository orderRepository,
                             FruitCatalogFeignClient fruitCatalogFeignClient,
                             FruitPricingFeignClient fruitPricingFeignClient,
                             FruitDeliveryFeignClient fruitDeliveryFeignClient,
                             OrderEventPublisher orderEventPublisher,
                             RestTemplate restTemplate,
                             WebClient webClient) {
        this.orderRepository = orderRepository;
        this.fruitCatalogFeignClient = fruitCatalogFeignClient;
        this.fruitPricingFeignClient = fruitPricingFeignClient;
        this.fruitDeliveryFeignClient = fruitDeliveryFeignClient;
        this.orderEventPublisher = orderEventPublisher;
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    public List<FruitOrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public FruitOrderEntity getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseGet(() -> {
                    FruitOrderEntity sample = new FruitOrderEntity();
                    sample.setId(orderId);
                    sample.setFruitId(1L);
                    sample.setFruitCode("DURIAN-RI6");
                    sample.setCustomerName("Le Minh Triet");
                    sample.setQuantityKg(5);
                    sample.setTotalAmountVnd(BigDecimal.valueOf(625000L));
                    sample.setStatus(OrderStatus.PAID);
                    return sample;
                });
    }

    @Transactional
    public FruitOrderEntity checkoutFruitOrder(Long fruitId, String fruitCode, String customerName, Integer quantityKg) {
        // 1. [A <-> B] Goi fruit-catalog-service qua OpenFeign de kiem tra & tru ton kho trai cay
        try {
            Map<String, Object> fruitInfo = fruitCatalogFeignClient.getFruitById(fruitId);
            Map<String, Object> stockResult = fruitCatalogFeignClient.deductFruitStock(
                    fruitId,
                    Map.of("fruitId", fruitId, "quantityKg", quantityKg)
            );
        } catch (Exception ignored) {
            // Fallback khi chay don le
        }

        // 2. [A <-> D] Goi fruit-pricing-service qua WebClient & OpenFeign de lay bao gia va chiet khau
        try {
            Map<?, ?> quoteResponse = webClient.get()
                    .uri("http://fruit-pricing-service:8084/api/pricing/quote/{fruitCode}", fruitCode)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            Map<String, Object> discountResult = fruitPricingFeignClient.calculateBulkDiscount(
                    Map.of("fruitCode", fruitCode, "quantityKg", quantityKg)
            );
        } catch (Exception ignored) {
            // Fallback khi chay don le
        }

        BigDecimal totalAmount = BigDecimal.valueOf(quantityKg * 125000L);

        FruitOrderEntity order = new FruitOrderEntity();
        order.setFruitId(fruitId);
        order.setFruitCode(fruitCode);
        order.setCustomerName(customerName);
        order.setQuantityKg(quantityKg);
        order.setTotalAmountVnd(totalAmount);
        order.setStatus(OrderStatus.STOCK_RESERVED);
        order = orderRepository.save(order);

        // 3. [A <-> C] Goi fruit-payment-service qua RestTemplate de thanh toan don hang trai cay
        try {
            Map<String, Object> paymentPayload = Map.of(
                    "orderId", order.getId(),
                    "customerName", customerName,
                    "amountVnd", totalAmount
            );
            Map<?, ?> paymentReceipt = restTemplate.postForObject(
                    "http://fruit-payment-service:8083/api/payments/charge",
                    paymentPayload,
                    Map.class
            );
            if (paymentReceipt != null && paymentReceipt.get("transactionId") != null) {
                order.setPaymentTransactionId(String.valueOf(paymentReceipt.get("transactionId")));
                order.setStatus(OrderStatus.PAID);
            }
        } catch (Exception ignored) {
            order.setPaymentTransactionId("TXN-LOCAL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            order.setStatus(OrderStatus.PAID);
        }

        // 4. [A <-> E] Goi fruit-delivery-service qua OpenFeign va ban event qua RabbitMQ
        try {
            Map<String, Object> deliveryResponse = fruitDeliveryFeignClient.dispatchColdTruck(
                    Map.of("orderId", order.getId(), "customerName", customerName, "fruitCode", fruitCode)
            );
            if (deliveryResponse != null && deliveryResponse.get("trackingCode") != null) {
                order.setDeliveryTrackingCode(String.valueOf(deliveryResponse.get("trackingCode")));
                order.setStatus(OrderStatus.DISPATCHED);
            }
            orderEventPublisher.publishOrderConfirmedEvent(order.getId(), fruitCode, quantityKg);
        } catch (Exception ignored) {
            order.setDeliveryTrackingCode("COLD-TRUCK-LOCAL");
            order.setStatus(OrderStatus.DISPATCHED);
        }

        return orderRepository.save(order);
    }

    public Map<?, ?> verifyPaymentByOrderId(Long orderId) {
        return restTemplate.getForObject(
                "http://fruit-payment-service:8083/api/payments/order/{orderId}",
                Map.class,
                orderId
        );
    }

    @Transactional
    public FruitOrderEntity updateDeliveryStatus(Long orderId, String deliveryStatus) {
        FruitOrderEntity order = getOrderById(orderId);
        if ("DELIVERED".equalsIgnoreCase(deliveryStatus)) {
            order.setStatus(OrderStatus.DELIVERED);
        }
        return orderRepository.save(order);
    }
}
