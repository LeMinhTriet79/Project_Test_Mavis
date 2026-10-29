package vn.fpt.mavis.fruit.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "fruit-pricing-service", contextId = "fruit-pricing-service")
public interface FruitPricingFeignClient {

    @PostMapping("/api/pricing/discount/calculate")
    Map<String, Object> calculateBulkDiscount(@RequestBody Map<String, Object> discountRequest);
}
