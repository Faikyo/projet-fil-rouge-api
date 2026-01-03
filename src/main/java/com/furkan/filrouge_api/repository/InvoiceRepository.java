package com.furkan.filrouge_api.repository;

import com.furkan.filrouge_api.domain.model.Invoice;

import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepository {
    Invoice save(Invoice invoice);
    Optional<Invoice> findByOrderId(UUID orderId);
}
