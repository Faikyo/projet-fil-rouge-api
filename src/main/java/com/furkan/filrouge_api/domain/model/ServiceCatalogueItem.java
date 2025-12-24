package com.furkan.filrouge_api.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class ServiceCatalogueItem {

    private final UUID id;

    private String name;

    private BigDecimal price;

    private boolean active;

    public ServiceCatalogueItem(UUID id, String name, BigDecimal price, boolean active) {
        if(id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if(price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.active = active;
    }
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    @Override
    public String toString() {
        return "ServiceCatalogueItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", active=" + active +
                '}';
    }

    
}
