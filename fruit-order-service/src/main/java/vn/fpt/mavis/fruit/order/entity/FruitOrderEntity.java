package vn.fpt.mavis.fruit.order.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "fruit_orders")
public class FruitOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long fruitId;

    @Column(nullable = false)
    private String fruitCode;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private Integer quantityKg;

    @Column(nullable = false)
    private BigDecimal totalAmountVnd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.CREATED;

    private String paymentTransactionId;
    private String deliveryTrackingCode;
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getFruitId() { return fruitId; }
    public void setFruitId(Long fruitId) { this.fruitId = fruitId; }
    public String getFruitCode() { return fruitCode; }
    public void setFruitCode(String fruitCode) { this.fruitCode = fruitCode; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public Integer getQuantityKg() { return quantityKg; }
    public void setQuantityKg(Integer quantityKg) { this.quantityKg = quantityKg; }
    public BigDecimal getTotalAmountVnd() { return totalAmountVnd; }
    public void setTotalAmountVnd(BigDecimal totalAmountVnd) { this.totalAmountVnd = totalAmountVnd; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public String getPaymentTransactionId() { return paymentTransactionId; }
    public void setPaymentTransactionId(String paymentTransactionId) { this.paymentTransactionId = paymentTransactionId; }
    public String getDeliveryTrackingCode() { return deliveryTrackingCode; }
    public void setDeliveryTrackingCode(String deliveryTrackingCode) { this.deliveryTrackingCode = deliveryTrackingCode; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
