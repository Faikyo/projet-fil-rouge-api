package com.furkan.filrouge_api.domain.service;

import com.furkan.filrouge_api.domain.exception.BusinessRuleException;
import com.furkan.filrouge_api.domain.model.Order;
import com.furkan.filrouge_api.domain.model.User;
import com.furkan.filrouge_api.domain.value.Role;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {

    @Test
    void inactiveUserCannotCreateOrder() {
        User user = new User(UUID.randomUUID(), "test@mail.com", Role.CLIENT, false);
        OrderFactory factory = new OrderFactory();

        assertThrows(BusinessRuleException.class, () -> factory.createOrder(user));
    }

    @Test
    void activeUserCanCreateOrder() {
        User user = new User(UUID.randomUUID(), "test@mail.com", Role.CLIENT, true);
        OrderFactory factory = new OrderFactory();

        Order order = factory.createOrder(user);

        assertNotNull(order);
        assertEquals(user.getId(), order.getCustomerId());
    }
}
