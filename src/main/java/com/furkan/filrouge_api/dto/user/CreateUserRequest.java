package com.furkan.filrouge_api.dto.user;

import com.furkan.filrouge_api.domain.value.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(
        @NotNull @Email String email,
        @NotNull Role role
) {}
