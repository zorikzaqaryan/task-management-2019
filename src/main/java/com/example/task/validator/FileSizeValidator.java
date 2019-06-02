package com.example.task.validator;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * File size impl
 */
public class FileSizeValidator implements ConstraintValidator<FileSizeValidatorConstraint, MultipartFile> {
    
    @Override
    public void initialize(FileSizeValidatorConstraint contactNumber) {
    }
    
    @Override
    public boolean isValid(MultipartFile f, ConstraintValidatorContext constraintValidatorContext) {
        return f.getSize() <= 5242870;
    }
    
}
