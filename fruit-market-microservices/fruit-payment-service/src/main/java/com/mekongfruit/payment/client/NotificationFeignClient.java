package com.mekongfruit.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@FeignClient(name = "fruit-notification-service", url = "http://fruit-notification-service:8086")
public interface NotificationFeignClient {

    @PostMapping("/api/v1/notifications/payment-receipt")
    Map<String, Object> sendPaymentReceiptNotification(@RequestBody Map<String, Object> payload);
}
