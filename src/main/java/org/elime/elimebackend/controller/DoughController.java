package org.elime.elimebackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.DoughCreateDto;
import org.elime.elimebackend.service.interfaces.DoughService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/doughs")
public class DoughController {
    private final DoughService doughService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> createDough(@Valid @RequestBody DoughCreateDto doughCreateDto) {
        doughService.createDough(doughCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
