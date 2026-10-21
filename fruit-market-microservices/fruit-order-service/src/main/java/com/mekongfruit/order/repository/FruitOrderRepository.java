package com.mekongfruit.order.repository;

import com.mekongfruit.order.entity.FruitOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FruitOrderRepository extends JpaRepository<FruitOrder, Long> {
    List<FruitOrder> findByCustomerEmail(String customerEmail);
}
