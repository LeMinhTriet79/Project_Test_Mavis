package vn.fpt.mavis.fruit.delivery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DeliveryHealthController {

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("service", "fruit-delivery-service", "status", "UP");
    }
}
