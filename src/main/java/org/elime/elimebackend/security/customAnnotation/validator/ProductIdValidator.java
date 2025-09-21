package org.elime.elimebackend.security.customAnnotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.repository.ProductRepository;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidProductId;

@RequiredArgsConstructor
public class ProductIdValidator implements ConstraintValidator<ValidProductId, String> {
    private final ProductRepository productRepository;
    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return productRepository.existsById(id);
    }
}
