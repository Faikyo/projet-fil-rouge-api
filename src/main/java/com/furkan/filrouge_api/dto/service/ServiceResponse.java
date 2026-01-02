package com.furkan.filrouge_api.dto.service;

import java.math.BigDecimal;
import java.util.UUID;

public record ServiceResponse(
        UUID id,
        String name,
        BigDecimal price,
        boolean active
) {}

