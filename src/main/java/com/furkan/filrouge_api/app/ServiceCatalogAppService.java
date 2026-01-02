package com.furkan.filrouge_api.app;

import com.furkan.filrouge_api.domain.exception.BusinessRuleException;
import com.furkan.filrouge_api.domain.model.ServiceCatalogItem;
import com.furkan.filrouge_api.dto.service.CreateServiceRequest;
import com.furkan.filrouge_api.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceCatalogAppService {

    private final ServiceRepository repo;

    public ServiceCatalogAppService(ServiceRepository repo) {
        this.repo = repo;
    }

    public ServiceCatalogItem create(CreateServiceRequest req) {
        // domaine = validation prix > 0 déjà chez toi (tu as dit oui)
        ServiceCatalogItem s = new ServiceCatalogItem(UUID.randomUUID(), req.name(), req.price(), true);
        return repo.save(s);
    }

    public ServiceCatalogItem get(UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Service not found: " + id));
    }

    public List<ServiceCatalogItem> list() {
        return repo.findAll();
    }

    public ServiceCatalogItem activate(UUID id) {
        ServiceCatalogItem s = get(id);
        s.activate();
        return repo.save(s);
    }

    public ServiceCatalogItem deactivate(UUID id) {
        ServiceCatalogItem s = get(id);
        s.deactivate();
        return repo.save(s);
    }
}

