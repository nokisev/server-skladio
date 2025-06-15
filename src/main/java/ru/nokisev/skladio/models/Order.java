package ru.nokisev.skladio.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nokisev.skladio.models.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate = LocalDateTime.now();

    private String orderNumber = "ORD-" + orderDate.getYear() + "-" + id;

    private String customer;

    private String email;

    private String address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CONFIRMED;

    private LocalDateTime expectedDeliveryDate = orderDate.plusDays(30);

    private int totalAmount;

    private String notes;

    @OneToMany
    private List<OrderItem> orderItems;

    @ManyToOne
    private User user;
}
