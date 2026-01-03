package com.furkan.filrouge_api.dto.invoice;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record InvoiceResponse(
        UUID id,
        UUID orderId,
        Instant issuedAt,
        BigDecimal amount
) {}
