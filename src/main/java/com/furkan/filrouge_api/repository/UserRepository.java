package com.furkan.filrouge_api.repository;

import com.furkan.filrouge_api.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
}
