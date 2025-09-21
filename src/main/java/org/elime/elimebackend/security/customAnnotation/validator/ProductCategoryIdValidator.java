package org.elime.elimebackend.security.customAnnotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.repository.ProductCategoryRepository;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidProductCategoryId;

@RequiredArgsConstructor
public class ProductCategoryIdValidator implements ConstraintValidator<ValidProductCategoryId, String> {
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return productCategoryRepository.existsById(id);
    }
}
