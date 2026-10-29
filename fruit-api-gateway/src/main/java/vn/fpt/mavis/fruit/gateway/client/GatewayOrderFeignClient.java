package vn.fpt.mavis.fruit.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "fruit-order-service", url = "http://fruit-order-service:8081")
public interface GatewayOrderFeignClient {

    @GetMapping("/api/orders")
    List<Map<String, Object>> listAllOrders();

    @GetMapping("/api/orders/{orderId}")
    Map<String, Object> getOrderDetails(@PathVariable("orderId") Long orderId);

    @PostMapping("/api/orders/checkout")
    Map<String, Object> submitCheckout(@RequestBody Map<String, Object> checkoutPayload);
}
