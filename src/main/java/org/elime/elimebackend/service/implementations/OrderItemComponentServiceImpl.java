package org.elime.elimebackend.service.implementations;

import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.OrderItemComponentCreateDto;
import org.elime.elimebackend.data.entities.OrderItemComponent;
import org.elime.elimebackend.service.interfaces.OrderItemComponentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemComponentServiceImpl implements OrderItemComponentService {
    private final ModelMapper modelMapper;

    @Override
    public OrderItemComponent createOrderItemComponent(OrderItemComponentCreateDto orderItemComponent) {
        return modelMapper.map(orderItemComponent, OrderItemComponent.class);
    }
}
