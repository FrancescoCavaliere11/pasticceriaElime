package org.elime.elimebackend.security.customAnnotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.repository.UserRepository;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidEmail;

@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByEmail(email).isEmpty();
    }
}
