package ru.nokisev.skladio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nokisev.skladio.models.ShipmentItem;

@Repository
public interface ShipmentItemRepository extends JpaRepository<ShipmentItem, Long> {
}
