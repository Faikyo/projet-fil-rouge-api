package com.furkan.filrouge_api.controller;

import com.furkan.filrouge_api.app.UserAppService;
import com.furkan.filrouge_api.mapper.UserMapper;
import com.furkan.filrouge_api.dto.user.CreateUserRequest;
import com.furkan.filrouge_api.dto.user.UserResponse;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserAppService service;

    public UserController(UserAppService service) {
        this.service = service;
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody CreateUserRequest req) {
        return UserMapper.toResponse(service.create(req));
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable UUID id) {
        return UserMapper.toResponse(service.get(id));
    }

    @PatchMapping("/{id}/activate")
    public UserResponse activate(@PathVariable UUID id) {
        return UserMapper.toResponse(service.activate(id));
    }

    @PatchMapping("/{id}/deactivate")
    public UserResponse deactivate(@PathVariable UUID id) {
        return UserMapper.toResponse(service.deactivate(id));
    }
}
