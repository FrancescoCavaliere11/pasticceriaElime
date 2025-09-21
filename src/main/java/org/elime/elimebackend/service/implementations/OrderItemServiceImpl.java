package org.elime.elimebackend.service.implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.OrderItemComponentCreateDto;
import org.elime.elimebackend.data.dto.create.OrderItemCreateDto;
import org.elime.elimebackend.data.entities.*;
import org.elime.elimebackend.data.enumerators.OrderCategory;
import org.elime.elimebackend.data.repository.OrderRepository;
import org.elime.elimebackend.data.repository.ProductCategoryRepository;
import org.elime.elimebackend.data.repository.ProductRepository;
import org.elime.elimebackend.data.repository.SettingsRepository;
import org.elime.elimebackend.security.exception.customException.MaxDailyOrderExceededException;
import org.elime.elimebackend.service.interfaces.OrderItemComponentService;
import org.elime.elimebackend.service.interfaces.OrderItemCustomizationService;
import org.elime.elimebackend.service.interfaces.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final ModelMapper modelMapper;

    private final OrderItemCustomizationService customizationService;
    private final OrderItemComponentService componentService;

    private final SettingsRepository settingsRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;


    @Override
    public OrderItem createOrderItem(OrderItemCreateDto orderItemCreateDto, LocalDate orderDate, Settings settings) {
        OrderItem orderItem = modelMapper.map(orderItemCreateDto, OrderItem.class);

        Product product = productRepository.findById(orderItemCreateDto.getProductId()).orElseThrow(
                () -> new EntityNotFoundException("Prodotto non trovato")
        );

        ProductCategory category = product.getCategory();
        long orderCount = orderRepository.sumQuantitiesByCategoryIdAndDate(category.getId(), orderDate);
        int requestedQty = orderItem.getQuantity();

        // cake
        if(category.getName().equals(OrderCategory.Torte.name())) {
            int dailyLimit = settings.getMaxDailyCakes();

            if (orderCount + requestedQty > dailyLimit) {
                throw new MaxDailyOrderExceededException(
                        buildExceededMessage(category.getName(), dailyLimit, orderCount, requestedQty)
                );
            }

            // customization
            if(orderItemCreateDto.getCustomization() != null) {
                OrderItemCustomization customization = customizationService.createOrderItemCustomization(orderItemCreateDto.getCustomization());
                customization.setOrderItem(orderItem);
                orderItem.setCustomization(customization);
            }
        } else if (category.getName().equals(OrderCategory.Vassoi.name())) {    // tray
            int dailyLimit = settings.getMaxDailyTrays();

            if (orderCount + requestedQty > dailyLimit) {
                throw new MaxDailyOrderExceededException(
                        buildExceededMessage(category.getName(), dailyLimit, orderCount, requestedQty)
                );
            }

            // components
            if(orderItemCreateDto.getOrderItemsComponent() != null) {
                for(OrderItemComponentCreateDto componentDto : orderItemCreateDto.getOrderItemsComponent()) {
                    OrderItemComponent component = componentService.createOrderItemComponent(componentDto);
                    component.setOrderItem(orderItem);
                    orderItem.getOrderItemsComponent().add(component);
                }
            }
        }

        return orderItem;
    }


    private String buildExceededMessage(String itemType, int limit, long booked, int requested) {
        return String.format(
                "Limite massimo di %s giornaliere superato: massimo %d, già prenotate %d, " +
                        "stai cercando di aggiungerne %d.",
                itemType, limit, booked, requested
        );
    }
}
