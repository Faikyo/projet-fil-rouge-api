package com.furkan.filrouge_api.domain.service;

import com.furkan.filrouge_api.domain.exception.BusinessRuleException;
import com.furkan.filrouge_api.domain.model.Order;
import com.furkan.filrouge_api.domain.model.User;

public class OrderFactory {

    public Order createOrder(User customer) {
        if (customer == null) throw new BusinessRuleException("customer is required");
        if (!customer.isActive()) throw new BusinessRuleException("Inactive user cannot create an order");

        return new Order(customer.getId());
    }
}
