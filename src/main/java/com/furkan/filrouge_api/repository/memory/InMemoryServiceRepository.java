package com.furkan.filrouge_api.repository.memory;

import com.furkan.filrouge_api.domain.model.ServiceCatalogItem;
import com.furkan.filrouge_api.repository.ServiceRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryServiceRepository implements ServiceRepository {

    private final ConcurrentHashMap<UUID, ServiceCatalogItem> store = new ConcurrentHashMap<>();

    @Override
    public ServiceCatalogItem save(ServiceCatalogItem service) {
        store.put(service.getId(), service);
        return service;
    }

    @Override
    public Optional<ServiceCatalogItem> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<ServiceCatalogItem> findAll() {
        return new ArrayList<>(store.values());
    }
}
