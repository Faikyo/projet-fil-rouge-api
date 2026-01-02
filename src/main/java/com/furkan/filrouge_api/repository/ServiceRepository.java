package com.furkan.filrouge_api.repository;

import com.furkan.filrouge_api.domain.model.ServiceCatalogItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceRepository {
    ServiceCatalogItem save(ServiceCatalogItem service);
    Optional<ServiceCatalogItem> findById(UUID id);
    List<ServiceCatalogItem> findAll();
}
