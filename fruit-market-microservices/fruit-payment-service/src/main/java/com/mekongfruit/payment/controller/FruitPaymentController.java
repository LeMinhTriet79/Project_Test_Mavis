package com.mekongfruit.payment.controller;

import com.mekongfruit.payment.client.NotificationFeignClient;
import com.mekongfruit.payment.entity.FruitPaymentTransaction;
import com.mekongfruit.payment.repository.FruitPaymentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
public class FruitPaymentController {

    private final FruitPaymentRepository paymentRepository;
    private final NotificationFeignClient notificationFeignClient;

    public FruitPaymentController(
            FruitPaymentRepository paymentRepository,
            NotificationFeignClient notificationFeignClient) {
        this.paymentRepository = paymentRepository;
        this.notificationFeignClient = notificationFeignClient;
    }

    @PostMapping("/charge")
    public FruitPaymentTransaction chargeOrderPayment(@RequestBody FruitPaymentTransaction tx) {
        FruitPaymentTransaction saved = paymentRepository.save(tx);
        Map<String, Object> notifyPayload = new HashMap<>();
        notifyPayload.put("orderId", saved.getOrderId());
        notifyPayload.put("status", "PAID");
        notificationFeignClient.sendPaymentReceiptNotification(notifyPayload);
        return saved;
    }

    @GetMapping("/order/{orderId}")
    public FruitPaymentTransaction getPaymentByOrder(@PathVariable Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found for order: " + orderId));
    }
}
