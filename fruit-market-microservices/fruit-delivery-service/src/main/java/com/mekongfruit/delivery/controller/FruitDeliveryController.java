package com.mekongfruit.delivery.controller;

import com.mekongfruit.delivery.client.DeliveryNotificationClient;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/deliveries")
public class FruitDeliveryController {

    private final DeliveryNotificationClient notificationClient;

    public FruitDeliveryController(DeliveryNotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    @PostMapping("/schedule")
    public Map<String, Object> scheduleColdTruckDelivery(@RequestBody Map<String, Object> request) {
        notificationClient.sendColdChainDispatchAlert(request);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "COLD_TRUCK_SCHEDULED");
        response.put("targetTempCelsius", 6.5);
        return response;
    }

    @GetMapping("/order/{orderId}")
    public Map<String, Object> trackColdChainDelivery(@PathVariable Long orderId) {
        Map<String, Object> tracking = new HashMap<>();
        tracking.put("orderId", orderId);
        tracking.put("currentTempCelsius", 6.2);
        tracking.put("route", "Tien Giang Orchard -> HCMC Distribution Hub");
        return tracking;
    }
}
