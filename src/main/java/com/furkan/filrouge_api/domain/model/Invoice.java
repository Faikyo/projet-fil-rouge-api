package com.furkan.filrouge_api.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Invoice {
    private final UUID id;

    private final UUID orderId;

    private Instant issuedAt;

    private BigDecimal amount;

    public Invoice(UUID id, UUID orderId, Instant issuedAt, BigDecimal amount) {
        if(id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if(orderId == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        if(issuedAt == null) {
            throw new IllegalArgumentException("Issued at cannot be null");
        }
        if(amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be null or negative");
        }
        this.id = id;
        this.orderId = orderId;
        this.issuedAt = issuedAt;
        this.amount = amount;
    }
    public UUID getId() {
        return id;
    }
    
    public UUID getOrderId() {
        return orderId;
    }
    public Instant getIssuedAt() {
        return issuedAt;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", issuedAt=" + issuedAt +
                ", amount=" + amount +
                '}';
    }
}
