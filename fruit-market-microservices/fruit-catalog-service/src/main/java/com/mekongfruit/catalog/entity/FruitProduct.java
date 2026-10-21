package com.mekongfruit.catalog.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "fruit_products")
public class FruitProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String skuCode;

    @Column(nullable = false)
    private String fruitName; // VD: Sau Rieng Ri6 Cai Mon, Xoai Cat Hoa Loc

    private String orchardProvince; // VD: Ben Tre, Tien Giang, Vinh Long

    private String ripenessGrade; // GRADE_A_EXPORT, GRADE_B_DOMESTIC

    @Column(nullable = false)
    private BigDecimal pricePerKg;

    private Double storageTempCelsius; // Nhiet do bao quan lanh chuan

    public Long getId() { return id; }
    public String getSkuCode() { return skuCode; }
    public String getFruitName() { return fruitName; }
    public String getOrchardProvince() { return orchardProvince; }
    public BigDecimal getPricePerKg() { return pricePerKg; }
}
