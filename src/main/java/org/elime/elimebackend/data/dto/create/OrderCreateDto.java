package org.elime.elimebackend.data.dto.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderCreateDto {
    @Future(message = "La data di ritiro deve essere futura")
    @NotNull(message = "La data di ritiro è obbligatoria")
    private LocalDate collectionDate;

    @NotNull(message = "Il metodo di pagamento è obbligatorio")
    //todo @ValueOfEnum(enumClass = org.elime.elimebackend.data.enumerators.PaymentMethod.class, message = "Metodo di pagamento non valido")
    private String paymentMethod;

    @NotNull(message = "Il prezzo totale è obbligatorio")
    @Positive(message = "Il prezzo totale deve essere un numero positivo")
    private Double price;

    private List<@Valid OrderItemCreateDto> orderItems;
}
