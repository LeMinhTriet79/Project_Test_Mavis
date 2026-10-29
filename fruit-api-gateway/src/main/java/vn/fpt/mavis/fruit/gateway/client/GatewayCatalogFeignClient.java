package vn.fpt.mavis.fruit.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "fruit-catalog-service", url = "http://fruit-catalog-service:8082")
public interface GatewayCatalogFeignClient {

    @GetMapping("/api/fruits")
    List<Map<String, Object>> listCatalogFruits();

    @GetMapping("/api/fruits/{id}")
    Map<String, Object> getFruitById(@PathVariable("id") Long id);
}
