package com.furkan.filrouge_api.app;

import com.furkan.filrouge_api.domain.exception.BusinessRuleException;
import com.furkan.filrouge_api.domain.model.Invoice;
import com.furkan.filrouge_api.domain.model.Order;
import com.furkan.filrouge_api.domain.model.ServiceCatalogItem;
import com.furkan.filrouge_api.domain.model.User;
import com.furkan.filrouge_api.domain.service.OrderFactory;
import com.furkan.filrouge_api.dto.order.AddOrderItemRequest;
import com.furkan.filrouge_api.dto.order.CreateOrderRequest;
import com.furkan.filrouge_api.repository.*;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class OrderAppService {

    private final UserRepository userRepo;
    private final ServiceRepository serviceRepo;
    private final OrderRepository orderRepo;
    private final InvoiceRepository invoiceRepo;

    private final OrderFactory orderFactory = new OrderFactory();

    public OrderAppService(UserRepository userRepo,
                           ServiceRepository serviceRepo,
                           OrderRepository orderRepo,
                           InvoiceRepository invoiceRepo) {
        this.userRepo = userRepo;
        this.serviceRepo = serviceRepo;
        this.orderRepo = orderRepo;
        this.invoiceRepo = invoiceRepo;
    }

    public Order create(CreateOrderRequest req) {
        User customer = userRepo.findById(req.customerId())
                .orElseThrow(() -> new BusinessRuleException("User not found: " + req.customerId()));

        Order order = orderFactory.createOrder(customer);
        return orderRepo.save(order);
    }

    public Order get(UUID orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new BusinessRuleException("Order not found: " + orderId));
    }

    public Order addItem(UUID orderId, AddOrderItemRequest req) {
        Order order = get(orderId);

        ServiceCatalogItem service = serviceRepo.findById(req.serviceId())
                .orElseThrow(() -> new BusinessRuleException("Service not found: " + req.serviceId()));

        if (!service.isActive()) {
            throw new BusinessRuleException("Service is inactive: " + service.getId());
        }

        order.addItem(service.getId(), service.getPrice(), req.quantity());
        return orderRepo.save(order);
    }

    public Order removeItem(UUID orderId, UUID itemId) {
        Order order = get(orderId);
        order.removeItem(itemId);
        return orderRepo.save(order);
    }

    public Order validate(UUID orderId) {
        Order order = get(orderId);
        order.validateOrder();
        return orderRepo.save(order);
    }

    public Order cancel(UUID orderId) {
        Order order = get(orderId);
        order.cancel();
        return orderRepo.save(order);
    }

    public Invoice pay(UUID orderId) {
        Order order = get(orderId);
        order.pay();
        orderRepo.save(order);

        Invoice invoice = new Invoice(UUID.randomUUID(), order.getId(), Instant.now(), order.total());
        return invoiceRepo.save(invoice);
    }

    public Invoice getInvoice(UUID orderId) {
        return invoiceRepo.findByOrderId(orderId)
                .orElseThrow(() -> new BusinessRuleException("Invoice not found for order: " + orderId));
    }
}
