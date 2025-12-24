package com.furkan.filrouge_api.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {

    private final UUID id;

    private final UUID serviceId;

    private final BigDecimal unitPrice;

    private int quantity;

    public OrderItem(UUID id, UUID serviceId, BigDecimal unitPrice, int quantity) {
        if(id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if(serviceId == null) {
            throw new IllegalArgumentException("Service ID cannot be null");
        }
        if(unitPrice == null||unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative");
        }
        if(quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.id = id;
        this.serviceId = serviceId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
    public UUID getId() {
        return id;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

     public BigDecimal lineTotal() {
         return unitPrice.multiply(BigDecimal.valueOf(quantity));
     }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }

}
