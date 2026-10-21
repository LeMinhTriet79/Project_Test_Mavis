package com.mekongfruit.inventory.controller;

import com.mekongfruit.inventory.entity.HarvestStockLot;
import com.mekongfruit.inventory.service.FruitInventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class FruitInventoryController {

    private final FruitInventoryService inventoryService;

    public FruitInventoryController(FruitInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/stock/{skuCode}")
    public HarvestStockLot getAvailableWeightKg(@PathVariable String skuCode) {
        return inventoryService.getStockBySku(skuCode);
    }

    @PostMapping("/reserve")
    public HarvestStockLot reserveFruitStock(@RequestParam String skuCode, @RequestParam Double weightKg) {
        return inventoryService.reserveFruitWeight(skuCode, weightKg);
    }
}
