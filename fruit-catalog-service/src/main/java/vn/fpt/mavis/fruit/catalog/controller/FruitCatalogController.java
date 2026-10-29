package vn.fpt.mavis.fruit.catalog.controller;

import org.springframework.web.bind.annotation.*;
import vn.fpt.mavis.fruit.catalog.entity.FruitProductEntity;
import vn.fpt.mavis.fruit.catalog.service.FruitCatalogService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fruits")
public class FruitCatalogController {

    private final FruitCatalogService fruitCatalogService;

    public FruitCatalogController(FruitCatalogService fruitCatalogService) {
        this.fruitCatalogService = fruitCatalogService;
    }

    @GetMapping
    public List<FruitProductEntity> listCatalogFruits() {
        return fruitCatalogService.listAllFruits();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getFruitById(@PathVariable("id") Long id) {
        return fruitCatalogService.getFruitWithSeasonalPrice(id);
    }

    @PostMapping("/{id}/deduct-stock")
    public Map<String, Object> deductFruitStock(@PathVariable("id") Long id,
                                                @RequestBody Map<String, Object> deductRequest) {
        Integer qty = deductRequest.get("quantityKg") != null
                ? Integer.valueOf(String.valueOf(deductRequest.get("quantityKg")))
                : 1;
        return fruitCatalogService.deductStock(id, qty);
    }
}
