package org.elime.elimebackend.security.customAnnotation.annotation;

import jakarta.validation.Constraint;
import org.elime.elimebackend.security.customAnnotation.validator.ProductCategoryIdValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {ProductCategoryIdValidator.class})
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProductCategoryId {
    String message() default "Id della categoria prodotto non valido";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
