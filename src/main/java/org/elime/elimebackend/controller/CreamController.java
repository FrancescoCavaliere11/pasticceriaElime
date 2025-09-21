package org.elime.elimebackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.CreamCreateDto;
import org.elime.elimebackend.service.interfaces.CreamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/creams")
@RequiredArgsConstructor
public class CreamController {
    private final CreamService creamService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> createCream(@Valid @RequestBody CreamCreateDto creamCreateDto) {
        creamService.createCream(creamCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
