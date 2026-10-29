package vn.fpt.mavis.fruit.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.mavis.fruit.payment.entity.FruitPaymentEntity;

import java.util.Optional;

@Repository
public interface FruitPaymentRepository extends JpaRepository<FruitPaymentEntity, Long> {
    Optional<FruitPaymentEntity> findByOrderId(Long orderId);
}
