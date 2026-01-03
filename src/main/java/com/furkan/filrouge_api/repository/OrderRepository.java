package com.furkan.filrouge_api.repository;

import com.furkan.filrouge_api.domain.model.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(UUID id);
}
