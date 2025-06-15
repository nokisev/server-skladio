package ru.nokisev.skladio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nokisev.skladio.models.Order;
import ru.nokisev.skladio.models.OrderItem;
import ru.nokisev.skladio.models.enums.OrderStatus;
import ru.nokisev.skladio.repositories.OrderItemRepository;
import ru.nokisev.skladio.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order createOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        orderItemRepository.saveAll(order.getOrderItems());
        return orderRepository.save(order);
    }

    public Order updateStatusOrder(Long id, OrderStatus status) {
        Order oldOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        oldOrder.setStatus(status);
        return orderRepository.save(oldOrder);
    }
    
}
