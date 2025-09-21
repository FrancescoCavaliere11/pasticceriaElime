package org.elime.elimebackend.service.implementations;

import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.OrderItemCustomizationCreateDto;
import org.elime.elimebackend.data.entities.OrderItemCustomization;
import org.elime.elimebackend.service.interfaces.OrderItemCustomizationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemCustomizationServiceImpl implements OrderItemCustomizationService {
    private final ModelMapper modelMapper;
    @Override
    public OrderItemCustomization createOrderItemCustomization(OrderItemCustomizationCreateDto orderItemCustomization) {
        return modelMapper.map(orderItemCustomization, OrderItemCustomization.class);
    }
}
