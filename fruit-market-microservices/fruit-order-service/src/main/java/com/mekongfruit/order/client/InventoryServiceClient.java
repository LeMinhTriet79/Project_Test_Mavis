package com.mekongfruit.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@FeignClient(name = "fruit-inventory-service", url = "http://fruit-inventory-service:8082")
public interface InventoryServiceClient {

    @PostMapping("/api/v1/inventory/reserve")
    Map<String, Object> reserveStockForOrder(
            @RequestParam("skuCode") String skuCode,
            @RequestParam("weightKg") Double weightKg);
}
