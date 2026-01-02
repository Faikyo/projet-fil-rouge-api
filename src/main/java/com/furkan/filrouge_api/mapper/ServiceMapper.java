package com.furkan.filrouge_api.mapper;

import com.furkan.filrouge_api.domain.model.ServiceCatalogItem;
import com.furkan.filrouge_api.dto.service.ServiceResponse;

public class ServiceMapper {

    private ServiceMapper() {}

    public static ServiceResponse toResponse(ServiceCatalogItem s) {
        return new ServiceResponse(s.getId(), s.getName(), s.getPrice(), s.isActive());
    }
}

