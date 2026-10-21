package com.mekongfruit.inventory.service;

import com.mekongfruit.inventory.entity.HarvestStockLot;
import com.mekongfruit.inventory.repository.HarvestStockLotRepository;
import org.springframework.stereotype.Service;

@Service
public class FruitInventoryService {

    private final HarvestStockLotRepository stockLotRepository;

    public FruitInventoryService(HarvestStockLotRepository stockLotRepository) {
        this.stockLotRepository = stockLotRepository;
    }

    public HarvestStockLot getStockBySku(String skuCode) {
        return stockLotRepository.findFirstBySkuCode(skuCode)
                .orElseThrow(() -> new IllegalArgumentException("No stock lot for SKU: " + skuCode));
    }

    public HarvestStockLot reserveFruitWeight(String skuCode, Double weightKg) {
        HarvestStockLot lot = getStockBySku(skuCode);
        lot.setAvailableKg(lot.getAvailableKg() - weightKg);
        lot.setReservedKg(lot.getReservedKg() + weightKg);
        return stockLotRepository.save(lot);
    }
}
