package com.furkan.filrouge_api.domain.model;

import com.furkan.filrouge_api.domain.exception.BusinessRuleException;
import com.furkan.filrouge_api.domain.exception.InvalidStateTransitionException;
import com.furkan.filrouge_api.domain.value.OrderStatus;

import java.math.BigDecimal;
import java.util.*;

public class Order {

    private final UUID id;

    private final UUID customerId;

    private OrderStatus status;

    private final List<OrderItem> items;

    
    public Order(UUID customerId) {
        if (customerId == null) throw new BusinessRuleException("customerId is required");
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.status = OrderStatus.CREATED;
        this.items = new ArrayList<>();
    }

    public UUID getId() { return id; }
    public UUID getCustomerId() { return customerId; }
    public OrderStatus getStatus() { return status; }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

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
            throw new BusinessRuleException("Order must have at least one item to be validated.");
        }
        status = OrderStatus.VALIDATED;
    }

    public void pay() {
        if (status != OrderStatus.VALIDATED) {
            throw new InvalidStateTransitionException("Only VALIDATED orders can be paid.");
        }
        status = OrderStatus.PAID;
    }

    public void cancel() {
        if (status == OrderStatus.PAID) {
            throw new InvalidStateTransitionException("Paid orders cannot be cancelled.");
        }
        if (status == OrderStatus.CANCELLED) return; // idempotent, optionnel
        status = OrderStatus.CANCELLED;
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
