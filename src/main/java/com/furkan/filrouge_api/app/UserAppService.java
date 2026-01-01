package com.furkan.filrouge_api.app;

import com.furkan.filrouge_api.domain.exception.BusinessRuleException;
import com.furkan.filrouge_api.domain.model.User;
import com.furkan.filrouge_api.repository.UserRepository;
import com.furkan.filrouge_api.dto.user.CreateUserRequest;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAppService {

    private final UserRepository repo;

    public UserAppService(UserRepository repo) {
        this.repo = repo;
    }

    public User create(CreateUserRequest req) {
        // Ici, on décide que créer = actif par défaut
        User user = new User(UUID.randomUUID(), req.email(), req.role(), true);
        return repo.save(user);
    }

    public User get(UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new BusinessRuleException("User not found: " + id));
    }

    public User activate(UUID id) {
        User u = get(id);
        u.activate();
        return repo.save(u);
    }

    public User deactivate(UUID id) {
        User u = get(id);
        u.deactivate();
        return repo.save(u);
    }
}

