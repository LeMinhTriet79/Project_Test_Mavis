package vn.fpt.mavis.fruit.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "fruit-delivery-service", url = "http://fruit-delivery-service:8085")
public interface FruitDeliveryFeignClient {

    @PostMapping("/api/deliveries/dispatch")
    Map<String, Object> dispatchColdTruck(@RequestBody Map<String, Object> deliveryRequest);
}
