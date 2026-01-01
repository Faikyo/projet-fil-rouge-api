package com.furkan.filrouge_api.mapper;

import com.furkan.filrouge_api.domain.model.User;
import com.furkan.filrouge_api.dto.user.UserResponse;

public class UserMapper {

    private UserMapper() {}

    public static UserResponse toResponse(User u) {
        return new UserResponse(u.getId(), u.getEmail(), u.getRole(), u.isActive());
    }
}

