package com.furkan.filrouge_api.domain.model;

import java.util.UUID;

import com.furkan.filrouge_api.domain.value.Role;

public class User {
    private final UUID id;
    private String email;
    private Role role;
    private boolean active;

    public User(UUID id, String email, Role role, boolean active) {
        if(id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if(email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if(role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        if(active == false && role == Role.ADMIN) {
            throw new IllegalArgumentException("Admin users must be active");
        }
        this.id = id;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", active=" + active +
                '}';
    }

    public boolean activate() {
        if (!this.active) {
            this.active = true;
            return true;
        }
        return false;
    }
    public boolean deactivate() {
        if (this.active) {
            this.active = false;
            return true;
        }
        return false;
    }
}
