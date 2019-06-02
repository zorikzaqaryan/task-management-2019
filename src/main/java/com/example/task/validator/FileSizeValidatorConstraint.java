package com.example.task.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * File validator for image files
 */
@Documented
@Constraint(validatedBy = FileSizeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSizeValidatorConstraint {
    String message() default "File size should be less than 5MB";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
