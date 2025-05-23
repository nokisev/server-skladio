package ru.nokisev.skladio.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nokisev.skladio.models.enums.ShipmentMethod;
import ru.nokisev.skladio.models.enums.ShipmentStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shipments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Order order;

    private LocalDateTime shipmentDate = LocalDateTime.now();
    private String shipmentNumber = "SHIP-" + shipmentDate.getYear() + "-" + id;
    @Enumerated(EnumType.STRING)
    private ShipmentStatus status = ShipmentStatus.PREPARING;
    @Enumerated(EnumType.STRING)
    private ShipmentMethod shippingMethod = ShipmentMethod.PICKUP;
    private String warehouseId = "1";
    private String shippingAddress;
    private String responsibleUserId = "1";

    private String notes;

    @OneToMany
    private List<ShipmentItem> shipmentItems;
}
