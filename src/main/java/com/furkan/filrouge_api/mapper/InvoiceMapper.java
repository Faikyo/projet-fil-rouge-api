package com.furkan.filrouge_api.mapper;

import com.furkan.filrouge_api.domain.model.Invoice;
import com.furkan.filrouge_api.dto.invoice.InvoiceResponse;

public class InvoiceMapper {
    private InvoiceMapper() {}

    public static InvoiceResponse toResponse(Invoice inv) {
        return new InvoiceResponse(inv.getId(), inv.getOrderId(), inv.getIssuedAt(), inv.getAmount());
    }
}

