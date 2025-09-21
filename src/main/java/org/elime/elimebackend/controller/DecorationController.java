package org.elime.elimebackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.DecorationCreateDto;
import org.elime.elimebackend.service.interfaces.DecorationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/decorations")
@RequiredArgsConstructor
public class DecorationController {
    private final DecorationService decorationService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> createDecoration(@Valid @RequestBody DecorationCreateDto decorationCreateDto) {
        decorationService.createDecoration(decorationCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
