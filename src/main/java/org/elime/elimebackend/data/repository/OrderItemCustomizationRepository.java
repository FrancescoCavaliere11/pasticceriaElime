package org.elime.elimebackend.data.repository;

import org.elime.elimebackend.data.entities.OrderItemCustomization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemCustomizationRepository extends JpaRepository<OrderItemCustomization, String> {
}
