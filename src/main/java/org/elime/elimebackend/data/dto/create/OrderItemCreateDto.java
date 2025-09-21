package org.elime.elimebackend.data.dto.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidOrderItem;

import java.util.List;

@Data
@ValidOrderItem
public class OrderItemCreateDto {
    @NotNull(message = "Il prezzo è obbligatorio")
    @Positive(message = "Il prezzo deve essere un numero positivo")
    private Double price;

    @NotNull(message = "La quantità è obbligatoria")
    @Positive(message = "La quantità deve essere un numero positivo")
    private Integer quantity;

    @Valid
    private OrderItemCustomizationCreateDto customization;

    private List<@Valid OrderItemComponentCreateDto> orderItemsComponent;

    @NotBlank(message = "L'id del prodotto è obbligatorio")
    private String productId;
}
