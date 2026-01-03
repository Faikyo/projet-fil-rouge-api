package com.furkan.filrouge_api.dto.order;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponse(
        UUID id,
        UUID serviceId,
        BigDecimal unitPrice,
        int quantity,
        BigDecimal lineTotal
) {}

