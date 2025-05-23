package ru.nokisev.skladio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nokisev.skladio.repositories.ShipmentItemRepository;
import ru.nokisev.skladio.repositories.ShipmentRepository;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private ShipmentItemRepository shipmentItemRepository;
}
