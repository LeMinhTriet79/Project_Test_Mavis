package vn.fpt.mavis.fruit.catalog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CatalogHealthController {

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("service", "fruit-catalog-service", "status", "UP");
    }
}
