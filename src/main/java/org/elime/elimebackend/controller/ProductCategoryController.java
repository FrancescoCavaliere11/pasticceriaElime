package org.elime.elimebackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.ProductCategoryCreateDto;
import org.elime.elimebackend.service.interfaces.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-categories")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> createProductCategory(@Valid @RequestBody ProductCategoryCreateDto productCategoryCreateDto) {
        productCategoryService.createProductCategory(productCategoryCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
