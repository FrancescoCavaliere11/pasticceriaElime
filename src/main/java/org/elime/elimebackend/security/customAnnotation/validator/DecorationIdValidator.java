package org.elime.elimebackend.security.customAnnotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.repository.DecorationRepository;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidDecorationId;

@RequiredArgsConstructor
public class DecorationIdValidator implements ConstraintValidator<ValidDecorationId, String> {
    private final DecorationRepository decorationRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        if(id == null) return true;
        return decorationRepository.existsById(id);
    }
}
