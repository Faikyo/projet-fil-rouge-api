package com.furkan.filrouge_api.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddOrderItemRequest(
        @NotNull UUID serviceId,
        @Min(1) int quantity
) {}
