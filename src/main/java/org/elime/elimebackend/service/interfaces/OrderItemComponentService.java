package org.elime.elimebackend.service.interfaces;

import org.elime.elimebackend.data.dto.create.OrderItemComponentCreateDto;
import org.elime.elimebackend.data.entities.OrderItemComponent;

public interface OrderItemComponentService {
    OrderItemComponent createOrderItemComponent(OrderItemComponentCreateDto orderItemComponent);
}
