package org.elime.elimebackend.service.interfaces;

import org.elime.elimebackend.data.dto.create.OrderItemCustomizationCreateDto;
import org.elime.elimebackend.data.entities.OrderItemCustomization;

public interface OrderItemCustomizationService {
    OrderItemCustomization createOrderItemCustomization(OrderItemCustomizationCreateDto orderItemCustomization);
}
