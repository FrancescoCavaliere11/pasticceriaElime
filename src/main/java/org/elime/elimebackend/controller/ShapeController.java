package org.elime.elimebackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.ShapeCreateDto;
import org.elime.elimebackend.service.interfaces.ShapeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/shapes")
public class ShapeController {
    private final ShapeService shapeService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> createShape(@Valid @RequestBody ShapeCreateDto shapeCreateDto) {
        shapeService.createShape(shapeCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
