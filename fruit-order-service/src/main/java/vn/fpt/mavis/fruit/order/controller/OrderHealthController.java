package vn.fpt.mavis.fruit.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderHealthController {

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("service", "fruit-order-service", "status", "UP");
    }
}
