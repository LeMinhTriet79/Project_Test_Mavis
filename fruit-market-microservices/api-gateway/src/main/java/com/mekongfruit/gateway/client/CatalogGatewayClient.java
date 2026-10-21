package com.mekongfruit.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Map;

@FeignClient(name = "fruit-catalog-service", url = "http://fruit-catalog-service:8081")
public interface CatalogGatewayClient {

    @GetMapping("/api/v1/fruits")
    List<Map<String, Object>> getAllSeasonalFruits();

    @GetMapping("/api/v1/fruits/{fruitId}")
    Map<String, Object> getFruitDetails(@PathVariable("fruitId") Long fruitId);
}
