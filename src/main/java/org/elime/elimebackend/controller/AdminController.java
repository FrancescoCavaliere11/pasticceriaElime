package org.elime.elimebackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.AdminCreateDto;
import org.elime.elimebackend.service.interfaces.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<HttpStatus> createAdmin(@Valid @RequestBody AdminCreateDto adminCreateDto) {
        adminService.createAdmin(adminCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
