package org.elime.elimebackend.data.dto.create;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidEmail;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidPhoneNumber;

@Data
public class AdminCreateDto {
    @NotBlank
    @Size(min = 3, max = 50, message = "Il nome deve essere lungo almeno 3 e massimo 50 caratteri")
    private String name;

    @NotBlank
    @Size(min = 3, max = 50, message = "Il cognome deve essere lungo almeno 3 e massimo 50 caratteri")
    private String surname;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{10}$", message = "Il numero di telefono deve contenere 10 numeri")
    @ValidPhoneNumber
    private String phoneNumber;

    @Email(message = "Email non valida")
    @NotBlank
    @ValidEmail
    private String email;

    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "La password deve contenere almeno 8 caratteri, con almeno una maiuscola, una minuscola, un numero e un carattere speciale"
    )
    @NotBlank
    private String password;
}
