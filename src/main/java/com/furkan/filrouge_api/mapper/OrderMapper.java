package com.furkan.filrouge_api.mapper;

import com.furkan.filrouge_api.domain.model.Order;
import com.furkan.filrouge_api.domain.model.OrderItem;
import com.furkan.filrouge_api.dto.order.OrderItemResponse;
import com.furkan.filrouge_api.dto.order.OrderResponse;

import java.util.List;

public class OrderMapper {

    private OrderMapper() {}

    public static OrderResponse toResponse(Order o) {
        List<OrderItemResponse> items = o.getItems().stream()
                .map(OrderMapper::toItemResponse)
                .toList();

        return new OrderResponse(
                o.getId(),
                o.getCustomerId(),
                o.getStatus(),
                items,
                o.total()
        );
    }

    private static OrderItemResponse toItemResponse(OrderItem i) {
        return new OrderItemResponse(
                i.getId(),
                i.getServiceId(),
                i.getUnitPrice(),
                i.getQuantity(),
                i.lineTotal()
        );
    }
}
