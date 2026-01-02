package com.furkan.filrouge_api.controller;

import com.furkan.filrouge_api.app.ServiceCatalogAppService;
import com.furkan.filrouge_api.dto.service.CreateServiceRequest;
import com.furkan.filrouge_api.dto.service.ServiceResponse;
import com.furkan.filrouge_api.mapper.ServiceMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceCatalogAppService service;

    public ServiceController(ServiceCatalogAppService service) {
        this.service = service;
    }

    @PostMapping
    public ServiceResponse create(@Valid @RequestBody CreateServiceRequest req) {
        return ServiceMapper.toResponse(service.create(req));
    }

    @GetMapping("/{id}")
    public ServiceResponse get(@PathVariable UUID id) {
        return ServiceMapper.toResponse(service.get(id));
    }

    @GetMapping
    public List<ServiceResponse> list() {
        return service.list().stream().map(ServiceMapper::toResponse).toList();
    }

    @PatchMapping("/{id}/activate")
    public ServiceResponse activate(@PathVariable UUID id) {
        return ServiceMapper.toResponse(service.activate(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ServiceResponse deactivate(@PathVariable UUID id) {
        return ServiceMapper.toResponse(service.deactivate(id));
    }
}
