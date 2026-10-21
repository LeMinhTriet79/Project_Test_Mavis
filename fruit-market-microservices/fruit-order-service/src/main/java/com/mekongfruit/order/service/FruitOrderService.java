package com.mekongfruit.order.service;

import com.mekongfruit.order.client.CatalogServiceClient;
import com.mekongfruit.order.client.DeliveryServiceClient;
import com.mekongfruit.order.client.InventoryServiceClient;
import com.mekongfruit.order.client.PaymentServiceClient;
import com.mekongfruit.order.entity.FruitOrder;
import com.mekongfruit.order.repository.FruitOrderRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class FruitOrderService {

    private final FruitOrderRepository orderRepository;
    private final CatalogServiceClient catalogServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final PaymentServiceClient paymentServiceClient;
    private final DeliveryServiceClient deliveryServiceClient;

    public FruitOrderService(
            FruitOrderRepository orderRepository,
            CatalogServiceClient catalogServiceClient,
            InventoryServiceClient inventoryServiceClient,
            PaymentServiceClient paymentServiceClient,
            DeliveryServiceClient deliveryServiceClient) {
        this.orderRepository = orderRepository;
        this.catalogServiceClient = catalogServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
        this.paymentServiceClient = paymentServiceClient;
        this.deliveryServiceClient = deliveryServiceClient;
    }

    public FruitOrder placeFruitOrder(FruitOrder order, Long fruitId) {
        catalogServiceClient.verifyFruitPricing(fruitId);
        inventoryServiceClient.reserveStockForOrder(order.getSkuCode(), order.getOrderedWeightKg());

        FruitOrder savedOrder = orderRepository.save(order);

        Map<String, Object> paymentReq = new HashMap<>();
        paymentReq.put("orderId", savedOrder.getId());
        paymentReq.put("amount", savedOrder.getTotalAmount());
        paymentServiceClient.chargeOrderPayment(paymentReq);

        Map<String, Object> deliveryReq = new HashMap<>();
        deliveryReq.put("orderId", savedOrder.getId());
        deliveryServiceClient.scheduleColdTruckDelivery(deliveryReq);

        savedOrder.setOrderStatus("DISPATCHED");
        return orderRepository.save(savedOrder);
    }

    public FruitOrder getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
    }
}
