package org.elime.elimebackend.security.customAnnotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.repository.UserRepository;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidUserId;

@RequiredArgsConstructor
public class UserIdValidator implements ConstraintValidator<ValidUserId, String> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.existsById(id);
    }
}
