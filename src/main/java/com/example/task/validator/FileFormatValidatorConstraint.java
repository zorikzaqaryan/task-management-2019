package com.example.task.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * File validator for image format
 */
@Documented
@Constraint(validatedBy = FileFormatValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileFormatValidatorConstraint {
    String message() default "File format should be  PNG, JPEG, GIF";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
}
