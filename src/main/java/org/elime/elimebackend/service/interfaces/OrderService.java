package org.elime.elimebackend.service.interfaces;

import org.elime.elimebackend.data.dto.create.OrderCreateDto;
import org.springframework.web.multipart.MultipartFile;

public interface OrderService {
    void createOrder(OrderCreateDto orderCreateDto, MultipartFile cakeImageUrl);
}
