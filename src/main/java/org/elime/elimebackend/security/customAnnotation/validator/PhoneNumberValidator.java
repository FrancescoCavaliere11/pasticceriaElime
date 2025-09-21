package org.elime.elimebackend.security.customAnnotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.repository.UserRepository;
import org.elime.elimebackend.security.customAnnotation.annotation.ValidPhoneNumber;

@RequiredArgsConstructor
public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByPhoneNumber(phoneNumber).isEmpty();
    }
}
