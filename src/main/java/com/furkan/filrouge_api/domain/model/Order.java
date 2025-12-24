package com.furkan.filrouge_api.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.furkan.filrouge_api.domain.exception.BusinessRuleException;
import com.furkan.filrouge_api.domain.exception.InvalidStateTransitionException;
import com.furkan.filrouge_api.domain.value.OrderStatus;

public class Order {

    private final UUID id;

    private final UUID customerId;

    private OrderStatus status;

    private final List<OrderItem> items;

    public Order(UUID id, UUID customerId, OrderStatus status, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public List<OrderItem> getItems() {
        return items;
    }

    // Règle : Si PAID ou CANCELLED => plus de modification
    private void ensureModifiable() {
        if (status == OrderStatus.PAID || status == OrderStatus.CANCELLED) {
            throw new InvalidStateTransitionException("Order is not modifiable when status is " + status);
        }
    }
    
    public void addItem(UUID serviceId, BigDecimal unitPrice, int quantity) {
        ensureModifiable();
        items.add(new OrderItem(UUID.randomUUID(), serviceId, unitPrice, quantity));

    }

    public void removeItem(UUID itemId) {
        ensureModifiable();
        boolean removed = items.removeIf(i -> i.getId().equals(itemId));
        if (!removed) {
            throw new BusinessRuleException("Order item not found: " + itemId);
        }
    }

    public BigDecimal total() {
        return items.stream()
                .map(OrderItem::lineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void validateOrder() {
        ensureModifiable();
        if (status != OrderStatus.CREATED) {
            throw new InvalidStateTransitionException("Can only validate an order in CREATED status");
        }
        if (items.isEmpty()) {
            throw new IllegalStateException("Order must have at least one item to be validated.");
        }
        this.status = OrderStatus.VALIDATED;
    }

    public void pay() {
        if (status == OrderStatus.CANCELLED) {
            throw new InvalidStateTransitionException("Cannot pay a CANCELLED order");
        }
        if (this.status != OrderStatus.VALIDATED) {
            throw new IllegalStateException("Only validated orders can be paid.");
        }
        this.status = OrderStatus.PAID;
    }

    public void cancel() {
        if (this.status == OrderStatus.PAID) {
            throw new IllegalStateException("Paid orders cannot be cancelled.");
        }
        this.status = OrderStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", status=" + status +
                ", items=" + items +
                '}';
    }
}
