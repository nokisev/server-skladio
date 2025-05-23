package ru.nokisev.skladio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nokisev.skladio.models.Order;
import ru.nokisev.skladio.models.OrderItem;
import ru.nokisev.skladio.models.Shipment;
import ru.nokisev.skladio.models.ShipmentItem;
import ru.nokisev.skladio.models.enums.OrderStatus;
import ru.nokisev.skladio.models.enums.ShipmentMethod;
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
        return orderRepository.save(order);
    }

    public Order updateStatusOrder(Long id, OrderStatus status) {
        Order oldOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        oldOrder.setStatus(status);
        return orderRepository.save(oldOrder);
    }

    public Shipment sendOrderToShipment(Long id, Order order, String address, String notes) {
        Order order1 = orderRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.SHIPPED);
        ArrayList<OrderItem> orderItems = new ArrayList<>(order.getOrderItems());

        Shipment shipment = new Shipment();
        shipment.setShipmentDate(LocalDateTime.now());
        shipment.setOrder(order);
        if (order.getTotalAmount() > 300000) {
            shipment.setShippingMethod(ShipmentMethod.TRANSPORT_COMPANY);
        }
        shipment.setShippingAddress(address);

        shipment.setNotes(notes);

        ArrayList<ShipmentItem> shipmentItems = new ArrayList<>();
        for (OrderItem item : orderItems) {
            shipmentItems.add(fromOrderItemToShipmentItems(item, new ShipmentItem()));
        }
        shipment.setShipmentItems(shipmentItems);

        return shipment;
    }


    private ShipmentItem fromOrderItemToShipmentItems(OrderItem orderItem, ShipmentItem shipmentItem) {
        shipmentItem.setProduct(orderItem.getProduct());
        shipmentItem.setQuantity_shipped(orderItem.getQuantity());

        return shipmentItem;
    }
}
