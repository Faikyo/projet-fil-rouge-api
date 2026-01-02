package com.furkan.filrouge_api.config;

import com.furkan.filrouge_api.repository.UserRepository;
import com.furkan.filrouge_api.repository.memory.InMemoryUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.furkan.filrouge_api.repository.ServiceRepository;

@Configuration
public class RepositoryConfig {

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }
    @Bean
    public ServiceRepository serviceRepository() {
        return new com.furkan.filrouge_api.repository.memory.InMemoryServiceRepository();
    }

}
