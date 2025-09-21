package org.elime.elimebackend.security.customAnnotation.annotation;

import jakarta.validation.Constraint;
import org.elime.elimebackend.security.customAnnotation.validator.MultipartExtensionValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MultipartExtensionValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ValidMultipartExtension {
    String message() default "Invalid image file extension. Allowed extensions are: jpg, jpeg, png";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
