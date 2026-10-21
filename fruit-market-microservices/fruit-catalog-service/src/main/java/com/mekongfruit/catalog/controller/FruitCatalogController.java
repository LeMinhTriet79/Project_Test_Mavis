package com.mekongfruit.catalog.controller;

import com.mekongfruit.catalog.entity.FruitProduct;
import com.mekongfruit.catalog.service.FruitCatalogService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/fruits")
public class FruitCatalogController {

    private final FruitCatalogService fruitCatalogService;

    public FruitCatalogController(FruitCatalogService fruitCatalogService) {
        this.fruitCatalogService = fruitCatalogService;
    }

    @GetMapping
    public List<FruitProduct> getAllSeasonalFruits() {
        return fruitCatalogService.listAllFruits();
    }

    @GetMapping("/{fruitId}")
    public Map<String, Object> getFruitDetails(@PathVariable Long fruitId) {
        return fruitCatalogService.getFruitWithLiveStock(fruitId);
    }
}
