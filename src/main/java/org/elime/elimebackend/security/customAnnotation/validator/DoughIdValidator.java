package org.elime.elimebackend.security.customAnnotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.repository.DoughRepository;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidDoughId;

@RequiredArgsConstructor
public class DoughIdValidator implements ConstraintValidator<ValidDoughId, String> {
    private final DoughRepository doughRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return doughRepository.existsById(id);
    }
}
