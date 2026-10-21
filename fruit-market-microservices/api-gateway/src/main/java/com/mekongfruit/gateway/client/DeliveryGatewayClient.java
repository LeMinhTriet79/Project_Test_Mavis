package com.mekongfruit.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;

@FeignClient(name = "fruit-delivery-service", url = "http://fruit-delivery-service:8085")
public interface DeliveryGatewayClient {

    @GetMapping("/api/v1/deliveries/order/{orderId}")
    Map<String, Object> trackColdChainDelivery(@PathVariable("orderId") Long orderId);
}
