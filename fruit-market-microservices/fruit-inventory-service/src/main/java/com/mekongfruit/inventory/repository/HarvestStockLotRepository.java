package com.mekongfruit.inventory.repository;

import com.mekongfruit.inventory.entity.HarvestStockLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface HarvestStockLotRepository extends JpaRepository<HarvestStockLot, Long> {
    Optional<HarvestStockLot> findFirstBySkuCode(String skuCode);
}
