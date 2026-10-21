package com.mekongfruit.catalog.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;

@FeignClient(name = "fruit-inventory-service", url = "http://fruit-inventory-service:8082")
public interface InventoryFeignClient {

    @GetMapping("/api/v1/inventory/stock/{skuCode}")
    Map<String, Object> getAvailableWeightKg(@PathVariable("skuCode") String skuCode);
}
