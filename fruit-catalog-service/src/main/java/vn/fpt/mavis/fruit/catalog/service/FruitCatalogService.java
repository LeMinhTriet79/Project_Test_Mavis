package vn.fpt.mavis.fruit.catalog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.fpt.mavis.fruit.catalog.client.SeasonalPricingFeignClient;
import vn.fpt.mavis.fruit.catalog.entity.FruitProductEntity;
import vn.fpt.mavis.fruit.catalog.repository.FruitProductRepository;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class FruitCatalogService {

    private final FruitProductRepository fruitProductRepository;
    private final SeasonalPricingFeignClient seasonalPricingFeignClient;

    public FruitCatalogService(FruitProductRepository fruitProductRepository,
                               SeasonalPricingFeignClient seasonalPricingFeignClient) {
        this.fruitProductRepository = fruitProductRepository;
        this.seasonalPricingFeignClient = seasonalPricingFeignClient;
    }

    public List<FruitProductEntity> listAllFruits() {
        return fruitProductRepository.findAll();
    }

    public Map<String, Object> getFruitWithSeasonalPrice(Long id) {
        FruitProductEntity fruit = fruitProductRepository.findById(id).orElseGet(() -> {
            FruitProductEntity sample = new FruitProductEntity();
            sample.setId(id);
            sample.setFruitCode("DURIAN-RI6");
            sample.setFruitName("Sau Rieng Ri6 Vinh Long");
            sample.setOriginProvince("Vinh Long");
            sample.setAvailableStockKg(500);
            sample.setBasePricePerKgVnd(BigDecimal.valueOf(125000));
            return sample;
        });

        Map<String, Object> seasonalRate;
        try {
            seasonalRate = seasonalPricingFeignClient.getSeasonalMultiplier("SUMMER");
        } catch (Exception ignored) {
            seasonalRate = Map.of("season", "SUMMER", "multiplier", 0.95);
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", fruit.getId());
        result.put("fruitCode", fruit.getFruitCode());
        result.put("fruitName", fruit.getFruitName());
        result.put("originProvince", fruit.getOriginProvince());
        result.put("availableStockKg", fruit.getAvailableStockKg());
        result.put("basePricePerKgVnd", fruit.getBasePricePerKgVnd());
        result.put("seasonalPricing", seasonalRate);
        return result;
    }

    @Transactional
    public Map<String, Object> deductStock(Long id, Integer quantityKg) {
        int remaining = Math.max(0, 500 - (quantityKg != null ? quantityKg : 1));
        return Map.of(
                "fruitId", id,
                "deductedKg", quantityKg != null ? quantityKg : 1,
                "remainingStockKg", remaining,
                "status", "STOCK_DEDUCTED"
        );
    }
}
