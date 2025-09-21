package org.elime.elimebackend.data.dto.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.elime.elimebackend.data.enumerators.Role;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidEmail;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidPhoneNumber;

@Data
public class CustomerCreateDto {
    @NotBlank
    @Size(min = 3, max = 50, message = "Il nome deve essere compreso tra 3 e 50 caratteri")
    private String name;

    @NotBlank
    @Size(min = 3, max = 50, message = "Il cognome deve essere compreso tra 3 e 50 caratteri")
    private String surname;

    @NotBlank
    @Email(message = "Email non valida")
    @ValidEmail
    private String email;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "La password deve contenere almeno 8 caratteri, con almeno una maiuscola, una minuscola, un numero e un carattere speciale"
    )
    private String password;

    @NotBlank
    @ValidPhoneNumber
    @Pattern(regexp = "^\\+?[0-9]{10}$", message = "Il numero di telefono deve contenere 10 numeri")
    private String phoneNumber;

}
