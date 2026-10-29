package vn.fpt.mavis.fruit.payment.controller;

import org.springframework.web.bind.annotation.*;
import vn.fpt.mavis.fruit.payment.entity.FruitPaymentEntity;
import vn.fpt.mavis.fruit.payment.repository.FruitPaymentRepository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class FruitPaymentController {

    private final FruitPaymentRepository paymentRepository;

    public FruitPaymentController(FruitPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @PostMapping("/charge")
    public Map<String, Object> chargeOrderPayment(@RequestBody Map<String, Object> paymentRequest) {
        Long orderId = paymentRequest.get("orderId") != null
                ? Long.valueOf(String.valueOf(paymentRequest.get("orderId")))
                : 1L;
        BigDecimal amount = paymentRequest.get("amountVnd") != null
                ? new BigDecimal(String.valueOf(paymentRequest.get("amountVnd")))
                : BigDecimal.valueOf(250000);

        FruitPaymentEntity entity = new FruitPaymentEntity();
        entity.setOrderId(orderId);
        entity.setTransactionId("TXN-FRUIT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setAmountVnd(amount);
        entity.setPaymentStatus("SUCCESS");
        paymentRepository.save(entity);

        return Map.of(
                "orderId", orderId,
                "transactionId", entity.getTransactionId(),
                "amountVnd", amount,
                "paymentStatus", "SUCCESS"
        );
    }

    @GetMapping("/order/{orderId}")
    public Map<String, Object> getPaymentByOrder(@PathVariable("orderId") Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .map(p -> Map.<String, Object>of(
                        "orderId", p.getOrderId(),
                        "transactionId", p.getTransactionId(),
                        "amountVnd", p.getAmountVnd(),
                        "paymentStatus", p.getPaymentStatus()
                ))
                .orElse(Map.of("orderId", orderId, "paymentStatus", "NOT_FOUND"));
    }
}
