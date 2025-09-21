package org.elime.elimebackend.data.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidProductId;

@Data
public class OrderItemComponentCreateDto {
    @NotBlank(message = "L'id del prodotto è obbligatorio")
    @ValidProductId
    private String productId;

    @NotBlank(message = "La quantità è obbligatoria")
    @Positive(message = "La quantità deve essere un numero positivo")
    private Integer quantity;
}
