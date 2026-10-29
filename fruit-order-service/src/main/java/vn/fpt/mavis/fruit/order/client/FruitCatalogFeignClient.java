package vn.fpt.mavis.fruit.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "fruit-catalog-service", url = "http://fruit-catalog-service:8082")
public interface FruitCatalogFeignClient {

    @GetMapping("/api/fruits/{id}")
    Map<String, Object> getFruitById(@PathVariable("id") Long id);

    @PostMapping("/api/fruits/{id}/deduct-stock")
    Map<String, Object> deductFruitStock(@PathVariable("id") Long id, @RequestBody Map<String, Object> deductRequest);
}
