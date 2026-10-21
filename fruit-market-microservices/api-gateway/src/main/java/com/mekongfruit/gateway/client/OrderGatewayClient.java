package com.mekongfruit.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@FeignClient(name = "fruit-order-service", url = "http://fruit-order-service:8083")
public interface OrderGatewayClient {

    @PostMapping("/api/v1/orders")
    Map<String, Object> submitFruitOrder(@RequestBody Map<String, Object> orderPayload);

    @GetMapping("/api/v1/orders/{orderId}")
    Map<String, Object> getOrderDetails(@PathVariable("orderId") Long orderId);
}
