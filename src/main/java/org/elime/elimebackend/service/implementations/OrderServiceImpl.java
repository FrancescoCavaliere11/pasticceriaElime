package org.elime.elimebackend.service.implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.OrderCreateDto;
import org.elime.elimebackend.data.dto.create.OrderItemCreateDto;
import org.elime.elimebackend.data.entities.*;
import org.elime.elimebackend.data.enumerators.PaymentMethod;
import org.elime.elimebackend.data.repository.CustomerRepository;
import org.elime.elimebackend.data.repository.OrderRepository;
import org.elime.elimebackend.data.repository.SettingsRepository;
import org.elime.elimebackend.security.exception.customException.InvalidOrderDateException;
import org.elime.elimebackend.service.interfaces.OrderItemService;
import org.elime.elimebackend.service.interfaces.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final SettingsRepository settingsRepository;

    private final ModelMapper modelMapper;

    private final UserDetailServiceImpl userDetailService;
    private final OrderItemService orderItemService;


    @Override
    @Transactional
    public void createOrder(OrderCreateDto orderCreateDto, MultipartFile cakeImageUrl) {
        // todo check e salvataggio dell'immagine

        // todo check del price

        String currentUserEmail = userDetailService.getCurrentUserEmail();
        Customer currentUser = customerRepository.findByEmail(currentUserEmail).orElseThrow(
                () -> new EntityNotFoundException("Utente non trovato")
        );

        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(
                () -> new EntityNotFoundException("Impostazioni non trovate")
        );

        if(orderCreateDto.getCollectionDate().isBefore(LocalDate.now().plusDays(settings.getLeadTimeDays()))) {
            throw new InvalidOrderDateException(
                    "La data di ritiro deve essere almeno " + settings.getLeadTimeDays() + " giorni dopo la data odierna"
            );
        }

        Order order = modelMapper.map(orderCreateDto, Order.class);
        order.setPaymentMethod(PaymentMethod.valueOf(orderCreateDto.getPaymentMethod()));
        order.setUser(currentUser);

        for(OrderItemCreateDto item : orderCreateDto.getOrderItems()) {
            OrderItem orderItem = orderItemService.createOrderItem(item, order.getDate(), settings);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }

        orderRepository.save(order);
    }
}
