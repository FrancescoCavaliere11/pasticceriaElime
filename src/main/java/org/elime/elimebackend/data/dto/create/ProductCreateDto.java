package org.elime.elimebackend.data.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidMultipartExtension;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidProductCategoryId;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductCreateDto {
    @NotBlank(message = "Il nome è obbligatorio")
    @Size(min = 3, max = 50, message = "Il nome deve essere compreso tra 3 e 50 caratteri")
    private String name;

    @NotNull(message = "Il prezzo è obbligatorio")
    @Positive(message = "Il prezzo deve essere un numero positivo")
    private Double price;

    @NotBlank(message = "L'id della categoria non può essere vuoto")
    @ValidProductCategoryId
    private String categoryId;

    @NotNull(message = "L'immagine è obbligatoria")
    @ValidMultipartExtension
    private MultipartFile image;

    // todo vedere se gestire attivo
}
