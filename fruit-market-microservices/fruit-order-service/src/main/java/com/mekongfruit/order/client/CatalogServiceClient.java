package com.mekongfruit.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;

@FeignClient(name = "fruit-catalog-service", url = "http://fruit-catalog-service:8081")
public interface CatalogServiceClient {

    @GetMapping("/api/v1/fruits/{fruitId}")
    Map<String, Object> verifyFruitPricing(@PathVariable("fruitId") Long fruitId);
}
