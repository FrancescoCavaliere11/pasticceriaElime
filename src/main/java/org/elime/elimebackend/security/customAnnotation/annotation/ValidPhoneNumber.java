package org.elime.elimebackend.security.customAnnotation.annotation;

import jakarta.validation.Constraint;
import org.elime.elimebackend.security.customAnnotation.validator.EmailValidator;
import org.elime.elimebackend.security.customAnnotation.validator.PhoneNumberValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumber {
    String message() default "Il numero di telefono è già associata ad un altro account";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
