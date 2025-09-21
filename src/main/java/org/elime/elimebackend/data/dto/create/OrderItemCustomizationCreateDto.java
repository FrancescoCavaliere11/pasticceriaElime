package org.elime.elimebackend.data.dto.create;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.elime.elimebackend.security.customAnnotation.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Data
public class OrderItemCustomizationCreateDto {
    @Size(max = 100)
    private String textDecoration;

    @NotNull(message = "Il peso è obbligatorio")
    @Positive(message = "Il peso deve essere un numero positivo")
    private Integer weight;

    @ValidMultipartExtension
    private MultipartFile imgUrl;   // todo mi sa che va passata a parte

    @Size(max = 255)
    private String description;

    @Pattern(
            regexp = "^#([A-Fa-f0-9]{6})$",
            message = "Il colore deve essere nel formato esadecimale (es. #FFFFFF)"
    )
    private String colorHexadecimal;

    @NotBlank
    @ValidCreamId
    private String creamId;

    @ValidDecorationId
    private String decorationId;

    @ValidShapeId
    private String shapeId;

    @NotBlank
    @ValidDoughId
    private String doughId;
}
