package com.furkan.filrouge_api.config;

import com.furkan.filrouge_api.repository.UserRepository;
import com.furkan.filrouge_api.repository.memory.InMemoryInvoiceRepository;
import com.furkan.filrouge_api.repository.memory.InMemoryOrderRepository;
import com.furkan.filrouge_api.repository.memory.InMemoryServiceRepository;
import com.furkan.filrouge_api.repository.memory.InMemoryUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.furkan.filrouge_api.repository.ServiceRepository;
import com.furkan.filrouge_api.repository.InvoiceRepository;
import com.furkan.filrouge_api.repository.OrderRepository;

@Configuration
public class RepositoryConfig {

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }
    @Bean
    public ServiceRepository serviceRepository() {
        return new InMemoryServiceRepository();
    }
    @Bean
    public OrderRepository orderRepository() {
        return new InMemoryOrderRepository();
    }

    @Bean
    public InvoiceRepository invoiceRepository() {
        return new InMemoryInvoiceRepository();
    }


}
