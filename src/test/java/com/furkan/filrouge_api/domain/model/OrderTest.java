package com.furkan.filrouge_api.domain.model;

import com.furkan.filrouge_api.domain.exception.BusinessRuleException;
import com.furkan.filrouge_api.domain.exception.InvalidStateTransitionException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void cannotValidateEmptyOrder() {
        Order order = new Order(UUID.randomUUID());
        assertThrows(BusinessRuleException.class, order::validateOrder);
    }

    @Test
    void payOnlyAllowedAfterValidated() {
        Order order = new Order(UUID.randomUUID());
        assertThrows(InvalidStateTransitionException.class, order::pay);

        order.addItem(UUID.randomUUID(), new BigDecimal("10.00"), 2);
        order.validateOrder();
        assertDoesNotThrow(order::pay);
    }

    @Test
    void cannotModifyPaidOrder() {
        Order order = new Order(UUID.randomUUID());
        order.addItem(UUID.randomUUID(), new BigDecimal("10.00"), 1);
        order.validateOrder();
        order.pay();

        assertThrows(InvalidStateTransitionException.class,
                () -> order.addItem(UUID.randomUUID(), new BigDecimal("5.00"), 1));
    }

    @Test
    void cannotCancelPaidOrder() {
        Order order = new Order(UUID.randomUUID());
        order.addItem(UUID.randomUUID(), new BigDecimal("10.00"), 1);
        order.validateOrder();
        order.pay();

        assertThrows(InvalidStateTransitionException.class, order::cancel);
    }

    @Test
    void totalIsSumOfLineTotals() {
        Order order = new Order(UUID.randomUUID());
        order.addItem(UUID.randomUUID(), new BigDecimal("10.00"), 2); // 20
        order.addItem(UUID.randomUUID(), new BigDecimal("3.50"), 3);  // 10.50
        assertEquals(new BigDecimal("30.50"), order.total());
    }
}

