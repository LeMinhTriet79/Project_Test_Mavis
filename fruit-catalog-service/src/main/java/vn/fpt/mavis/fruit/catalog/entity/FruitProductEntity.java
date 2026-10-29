package vn.fpt.mavis.fruit.catalog.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "fruit_products")
public class FruitProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String fruitCode;

    @Column(nullable = false)
    private String fruitName;

    @Column(nullable = false)
    private String originProvince;

    @Column(nullable = false)
    private Integer availableStockKg;

    @Column(nullable = false)
    private BigDecimal basePricePerKgVnd;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFruitCode() { return fruitCode; }
    public void setFruitCode(String fruitCode) { this.fruitCode = fruitCode; }
    public String getFruitName() { return fruitName; }
    public void setFruitName(String fruitName) { this.fruitName = fruitName; }
    public String getOriginProvince() { return originProvince; }
    public void setOriginProvince(String originProvince) { this.originProvince = originProvince; }
    public Integer getAvailableStockKg() { return availableStockKg; }
    public void setAvailableStockKg(Integer availableStockKg) { this.availableStockKg = availableStockKg; }
    public BigDecimal getBasePricePerKgVnd() { return basePricePerKgVnd; }
    public void setBasePricePerKgVnd(BigDecimal basePricePerKgVnd) { this.basePricePerKgVnd = basePricePerKgVnd; }
}
