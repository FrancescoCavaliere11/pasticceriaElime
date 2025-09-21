package org.elime.elimebackend.data.repository;

import org.elime.elimebackend.data.entities.OrderItemComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemComponentRepository extends JpaRepository<OrderItemComponent, String> {
}
