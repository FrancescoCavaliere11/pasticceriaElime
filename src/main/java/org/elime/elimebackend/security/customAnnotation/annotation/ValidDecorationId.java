package org.elime.elimebackend.security.customAnnotation.annotation;

import jakarta.validation.Constraint;
import org.elime.elimebackend.security.customAnnotation.validator.DecorationIdValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {DecorationIdValidator.class})
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDecorationId {
    String message() default "Id della decorazione non valido";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
