package org.elime.elimebackend.security.customAnnotation.annotation;

import jakarta.validation.Constraint;
import org.elime.elimebackend.security.customAnnotation.validator.MultipartExtensionValidator;
import org.elime.elimebackend.security.customAnnotation.validator.ProductIdValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ProductIdValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ValidProductId {
    String message() default "Id del prodotto non valido";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
