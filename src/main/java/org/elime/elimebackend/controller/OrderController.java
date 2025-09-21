package org.elime.elimebackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.OrderCreateDto;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidMultipartExtension;
import org.elime.elimebackend.service.interfaces.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<HttpStatus> createOrder(
            @RequestPart("orderCreateDto") @Valid OrderCreateDto orderCreateDto,
            @ValidMultipartExtension @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        orderService.createOrder(orderCreateDto, image);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
