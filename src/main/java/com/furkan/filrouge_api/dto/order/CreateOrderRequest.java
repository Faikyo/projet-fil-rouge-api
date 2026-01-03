package com.furkan.filrouge_api.dto.order;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreateOrderRequest(
        @NotNull UUID customerId
) {}
