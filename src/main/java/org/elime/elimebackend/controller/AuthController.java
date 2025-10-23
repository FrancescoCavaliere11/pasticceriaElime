package org.elime.elimebackend.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.CustomerCreateDto;
import org.elime.elimebackend.data.dto.response.AuthResponseDto;
import org.elime.elimebackend.service.interfaces.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponseDto> login(
            @NotBlank(message = "Email o numero di telefono obbligatorio") @RequestParam String emailOrTelephoneNumber,
            @NotBlank(message = "La password è obbligatoria") @RequestParam String password
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.signIn(emailOrTelephoneNumber, password));
    }


    @PostMapping("/sign-up")
    public ResponseEntity<HttpStatus> register(@Valid @RequestBody CustomerCreateDto customerCreateDto) {
        authService.signUp(customerCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
    
}

