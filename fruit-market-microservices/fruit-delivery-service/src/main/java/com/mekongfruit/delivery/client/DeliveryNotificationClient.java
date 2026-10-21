package com.mekongfruit.delivery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@FeignClient(name = "fruit-notification-service", url = "http://fruit-notification-service:8086")
public interface DeliveryNotificationClient {

    @PostMapping("/api/v1/notifications/cold-chain-alert")
    Map<String, Object> sendColdChainDispatchAlert(@RequestBody Map<String, Object> alertPayload);
}
