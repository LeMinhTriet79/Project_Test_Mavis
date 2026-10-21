package com.mekongfruit.inventory.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "harvest_stock_lots")
public class HarvestStockLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String skuCode;

    private String coldStorageZone; // ZONE_CHILL_4C, ZONE_TROPICAL_12C

    private Double availableKg;

    private Double reservedKg;

    private LocalDate harvestDate;

    private LocalDate expiryDate;

    public Long getId() { return id; }
    public String getSkuCode() { return skuCode; }
    public Double getAvailableKg() { return availableKg; }
    public void setAvailableKg(Double availableKg) { this.availableKg = availableKg; }
    public Double getReservedKg() { return reservedKg; }
    public void setReservedKg(Double reservedKg) { this.reservedKg = reservedKg; }
}
