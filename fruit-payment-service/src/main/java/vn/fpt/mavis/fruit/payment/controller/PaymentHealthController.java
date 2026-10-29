package vn.fpt.mavis.fruit.payment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PaymentHealthController {

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("service", "fruit-payment-service", "status", "UP");
    }
}
