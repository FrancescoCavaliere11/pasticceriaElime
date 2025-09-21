package org.elime.elimebackend.security.customAnnotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.repository.ShapeRepository;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidShapeId;

@RequiredArgsConstructor
public class ShapeIdValidator implements ConstraintValidator<ValidShapeId, String> {
    private final ShapeRepository shapeRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        if(id == null)  return true;
        return shapeRepository.existsById(id);
    }
}
