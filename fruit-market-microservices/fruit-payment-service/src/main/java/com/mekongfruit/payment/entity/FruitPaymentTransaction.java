package com.mekongfruit.payment.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fruit_payment_transactions")
public class FruitPaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private BigDecimal amount;

    private String paymentMethod; // VNPAY_QR, MOMO_WALLET, BANK_TRANSFER

    private String paymentStatus; // COMPLETED, REFUNDED, FAILED

    private LocalDateTime paidAt;

    public Long getId() { return id; }
    public Long getOrderId() { return orderId; }
    public BigDecimal getAmount() { return amount; }
    public String getPaymentStatus() { return paymentStatus; }
}
