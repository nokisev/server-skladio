package ru.nokisev.skladio.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private int quantity = 0;

    private int pricePerUnit = product != null ? product.getPrice() : 0;

    private int discount = 0;

    private int totalPrice = (quantity * pricePerUnit) - discount;

}
