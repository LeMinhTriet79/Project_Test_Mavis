package com.mekongfruit.catalog.service;

import com.mekongfruit.catalog.client.InventoryFeignClient;
import com.mekongfruit.catalog.entity.FruitProduct;
import com.mekongfruit.catalog.repository.FruitProductRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FruitCatalogService {

    private final FruitProductRepository fruitProductRepository;
    private final InventoryFeignClient inventoryFeignClient;

    public FruitCatalogService(
            FruitProductRepository fruitProductRepository,
            InventoryFeignClient inventoryFeignClient) {
        this.fruitProductRepository = fruitProductRepository;
        this.inventoryFeignClient = inventoryFeignClient;
    }

    public List<FruitProduct> listAllFruits() {
        return fruitProductRepository.findAll();
    }

    public Map<String, Object> getFruitWithLiveStock(Long fruitId) {
        FruitProduct fruit = fruitProductRepository.findById(fruitId)
                .orElseThrow(() -> new IllegalArgumentException("Fruit not found: " + fruitId));
        Map<String, Object> stockInfo = inventoryFeignClient.getAvailableWeightKg(fruit.getSkuCode());
        Map<String, Object> response = new HashMap<>();
        response.put("fruit", fruit);
        response.put("warehouseStock", stockInfo);
        return response;
    }
}
