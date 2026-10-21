package com.mekongfruit.gateway.controller;

import com.mekongfruit.gateway.client.CatalogGatewayClient;
import com.mekongfruit.gateway.client.DeliveryGatewayClient;
import com.mekongfruit.gateway.client.OrderGatewayClient;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/storefront")
public class FruitStorefrontGatewayController {

    private final CatalogGatewayClient catalogGatewayClient;
    private final OrderGatewayClient orderGatewayClient;
    private final DeliveryGatewayClient deliveryGatewayClient;

    public FruitStorefrontGatewayController(
            CatalogGatewayClient catalogGatewayClient,
            OrderGatewayClient orderGatewayClient,
            DeliveryGatewayClient deliveryGatewayClient) {
        this.catalogGatewayClient = catalogGatewayClient;
        this.orderGatewayClient = orderGatewayClient;
        this.deliveryGatewayClient = deliveryGatewayClient;
    }

    @GetMapping("/catalog")
    public List<Map<String, Object>> browseFreshFruits() {
        return catalogGatewayClient.getAllSeasonalFruits();
    }

    @PostMapping("/checkout")
    public Map<String, Object> checkoutFruitBasket(@RequestBody Map<String, Object> payload) {
        return orderGatewayClient.submitFruitOrder(payload);
    }

    @GetMapping("/orders/{orderId}/tracking")
    public Map<String, Object> getOrderWithColdChainStatus(@PathVariable Long orderId) {
        Map<String, Object> result = new HashMap<>();
        result.put("order", orderGatewayClient.getOrderDetails(orderId));
        result.put("coldChainDelivery", deliveryGatewayClient.trackColdChainDelivery(orderId));
        return result;
    }
}
