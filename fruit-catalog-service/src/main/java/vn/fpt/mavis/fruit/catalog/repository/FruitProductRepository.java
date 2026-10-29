package vn.fpt.mavis.fruit.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.mavis.fruit.catalog.entity.FruitProductEntity;

import java.util.Optional;

@Repository
public interface FruitProductRepository extends JpaRepository<FruitProductEntity, Long> {
    Optional<FruitProductEntity> findByFruitCode(String fruitCode);
}
