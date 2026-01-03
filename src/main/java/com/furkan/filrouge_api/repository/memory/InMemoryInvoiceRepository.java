package com.furkan.filrouge_api.repository.memory;

import com.furkan.filrouge_api.domain.model.Invoice;
import com.furkan.filrouge_api.repository.InvoiceRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryInvoiceRepository implements InvoiceRepository {

    private final ConcurrentHashMap<UUID, Invoice> storeByOrderId = new ConcurrentHashMap<>();

    @Override
    public Invoice save(Invoice invoice) {
        storeByOrderId.put(invoice.getOrderId(), invoice);
        return invoice;
    }

    @Override
    public Optional<Invoice> findByOrderId(UUID orderId) {
        return Optional.ofNullable(storeByOrderId.get(orderId));
    }
}
