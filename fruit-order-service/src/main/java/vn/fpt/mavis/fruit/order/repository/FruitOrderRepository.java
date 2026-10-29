package vn.fpt.mavis.fruit.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.mavis.fruit.order.entity.FruitOrderEntity;
import vn.fpt.mavis.fruit.order.entity.OrderStatus;

import java.util.List;

@Repository
public interface FruitOrderRepository extends JpaRepository<FruitOrderEntity, Long> {
    List<FruitOrderEntity> findByStatus(OrderStatus status);
}
