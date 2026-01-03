package com.furkan.filrouge_api.controller;

import com.furkan.filrouge_api.app.OrderAppService;
import com.furkan.filrouge_api.dto.invoice.InvoiceResponse;
import com.furkan.filrouge_api.dto.order.*;
import com.furkan.filrouge_api.mapper.InvoiceMapper;
import com.furkan.filrouge_api.mapper.OrderMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderAppService service;

    public OrderController(OrderAppService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponse create(@Valid @RequestBody CreateOrderRequest req) {
        return OrderMapper.toResponse(service.create(req));
    }

    @GetMapping("/{orderId}")
    public OrderResponse get(@PathVariable UUID orderId) {
        return OrderMapper.toResponse(service.get(orderId));
    }

    @PostMapping("/{orderId}/items")
    public OrderResponse addItem(@PathVariable UUID orderId, @Valid @RequestBody AddOrderItemRequest req) {
        return OrderMapper.toResponse(service.addItem(orderId, req));
    }

    @DeleteMapping("/{orderId}/items/{itemId}")
    public OrderResponse removeItem(@PathVariable UUID orderId, @PathVariable UUID itemId) {
        return OrderMapper.toResponse(service.removeItem(orderId, itemId));
    }

    @PatchMapping("/{orderId}/validate")
    public OrderResponse validate(@PathVariable UUID orderId) {
        return OrderMapper.toResponse(service.validate(orderId));
    }

    @PatchMapping("/{orderId}/cancel")
    public OrderResponse cancel(@PathVariable UUID orderId) {
        return OrderMapper.toResponse(service.cancel(orderId));
    }

    @PatchMapping("/{orderId}/pay")
    public InvoiceResponse pay(@PathVariable UUID orderId) {
        return InvoiceMapper.toResponse(service.pay(orderId));
    }

    @GetMapping("/{orderId}/invoice")
    public InvoiceResponse getInvoice(@PathVariable UUID orderId) {
        return InvoiceMapper.toResponse(service.getInvoice(orderId));
    }
}
