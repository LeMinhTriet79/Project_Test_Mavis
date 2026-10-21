package com.mekongfruit.payment.repository;

import com.mekongfruit.payment.entity.FruitPaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FruitPaymentRepository extends JpaRepository<FruitPaymentTransaction, Long> {
    Optional<FruitPaymentTransaction> findByOrderId(Long orderId);
}
