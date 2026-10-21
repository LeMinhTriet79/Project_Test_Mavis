package com.mekongfruit.catalog.repository;

import com.mekongfruit.catalog.entity.FruitProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FruitProductRepository extends JpaRepository<FruitProduct, Long> {
    Optional<FruitProduct> findBySkuCode(String skuCode);
}
