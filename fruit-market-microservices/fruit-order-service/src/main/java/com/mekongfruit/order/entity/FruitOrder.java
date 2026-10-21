package com.mekongfruit.order.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fruit_orders")
public class FruitOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerEmail;

    private String shippingAddress;

    private String skuCode;

    private Double orderedWeightKg;

    private BigDecimal totalAmount;

    private String orderStatus; // CREATED, PAID, DISPATCHED, DELIVERED

    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public String getCustomerEmail() { return customerEmail; }
    public String getSkuCode() { return skuCode; }
    public Double getOrderedWeightKg() { return orderedWeightKg; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
}
