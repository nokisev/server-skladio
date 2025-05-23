package ru.nokisev.skladio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nokisev.skladio.models.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
