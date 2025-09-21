package org.elime.elimebackend.service.interfaces;

import org.elime.elimebackend.data.dto.create.OrderItemCreateDto;
import org.elime.elimebackend.data.entities.OrderItem;
import org.elime.elimebackend.data.entities.Settings;

import java.time.LocalDate;

public interface OrderItemService {
    OrderItem createOrderItem(OrderItemCreateDto orderItemCreateDto, LocalDate orderDate, Settings settings);
}
