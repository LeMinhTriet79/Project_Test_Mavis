package com.mekongfruit.notification.controller;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/notifications")
public class FruitNotificationController {

    @PostMapping("/payment-receipt")
    public Map<String, Object> sendPaymentReceiptNotification(@RequestBody Map<String, Object> payload) {
        Map<String, Object> res = new HashMap<>();
        res.put("delivered", true);
        res.put("channel", "EMAIL_RECEIPT");
        return res;
    }

    @PostMapping("/cold-chain-alert")
    public Map<String, Object> sendColdChainDispatchAlert(@RequestBody Map<String, Object> payload) {
        Map<String, Object> res = new HashMap<>();
        res.put("delivered", true);
        res.put("channel", "SMS_COLD_CHAIN_TRACKING");
        return res;
    }
}
