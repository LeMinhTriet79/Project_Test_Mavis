package vn.fpt.mavis.fruit.pricing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PricingHealthController {

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("service", "fruit-pricing-service", "status", "UP");
    }
}
