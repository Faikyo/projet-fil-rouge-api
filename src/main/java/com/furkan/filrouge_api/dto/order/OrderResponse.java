package com.furkan.filrouge_api.dto.order;

import com.furkan.filrouge_api.domain.value.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        UUID customerId,
        OrderStatus status,
        List<OrderItemResponse> items,
        BigDecimal total
) {}
