package vn.fpt.mavis.fruit.delivery.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/deliveries")
public class FruitDeliveryController {

    @PostMapping("/dispatch")
    public Map<String, Object> dispatchColdTruck(@RequestBody Map<String, Object> deliveryRequest) {
        Object orderId = deliveryRequest.getOrDefault("orderId", 1L);
        String trackingCode = "COLD-TRUCK-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return Map.of(
                "orderId", orderId,
                "trackingCode", trackingCode,
                "vehicleType", "REFRIGERATED_VAN_4C",
                "status", "DISPATCHED"
        );
    }

    @GetMapping("/track/{orderId}")
    public Map<String, Object> trackDelivery(@PathVariable("orderId") Long orderId) {
        return Map.of(
                "orderId", orderId,
                "temperatureCelsius", 4.5,
                "status", "IN_TRANSIT"
        );
    }
}
