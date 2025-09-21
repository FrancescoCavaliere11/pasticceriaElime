package org.elime.elimebackend.security.customAnnotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.repository.CreamRepository;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidCreamId;

@RequiredArgsConstructor
public class CreamIdValidator implements ConstraintValidator<ValidCreamId, String> {
    private final CreamRepository creamRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return creamRepository.existsById(id);
    }
}
