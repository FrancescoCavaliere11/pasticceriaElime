package org.elime.elimebackend.security.customAnnotation.validator;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.OrderItemCreateDto;
import org.elime.elimebackend.data.entities.Product;
import org.elime.elimebackend.data.enumerators.OrderCategory;
import org.elime.elimebackend.data.repository.ProductRepository;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidOrderItem;

@RequiredArgsConstructor
public class OrderItemValidator implements ConstraintValidator<ValidOrderItem, OrderItemCreateDto> {
    private final ProductRepository productRepository;

    @Override
    public boolean isValid(OrderItemCreateDto dto, ConstraintValidatorContext constraintValidatorContext) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato"));

        boolean hasCustomization = dto.getCustomization() != null;
        boolean hasComponents = dto.getOrderItemsComponent() != null && !dto.getOrderItemsComponent().isEmpty();

        OrderCategory orderCategory;
        try {
            orderCategory = OrderCategory.valueOf(product.getCategory().getName());
        } catch (IllegalArgumentException e) {
            return false; // categoria non supportata → non prenotabile
        }

        return switch (orderCategory) {
            case Torte -> hasCustomization && !hasComponents;
            case Vassoi -> hasComponents && !hasCustomization;
        };
    }
}
