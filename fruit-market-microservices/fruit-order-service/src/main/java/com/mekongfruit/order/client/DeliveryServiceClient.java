package com.mekongfruit.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@FeignClient(name = "fruit-delivery-service", url = "http://fruit-delivery-service:8085")
public interface DeliveryServiceClient {

    @PostMapping("/api/v1/deliveries/schedule")
    Map<String, Object> scheduleColdTruckDelivery(@RequestBody Map<String, Object> deliveryRequest);
}
