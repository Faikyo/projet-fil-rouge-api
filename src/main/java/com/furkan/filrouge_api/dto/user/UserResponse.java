package com.furkan.filrouge_api.dto.user;

import com.furkan.filrouge_api.domain.value.Role;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String email,
        Role role,
        boolean active
) {}

