package org.elime.elimebackend.security.customAnnotation.annotation;


import jakarta.validation.Constraint;
import org.elime.elimebackend.security.customAnnotation.validator.OrderItemValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {OrderItemValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOrderItem {
    String message() default "Ordine non valido";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
