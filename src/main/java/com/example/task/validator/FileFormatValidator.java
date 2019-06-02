package com.example.task.validator;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * File format impl
 */
public class FileFormatValidator implements ConstraintValidator<FileFormatValidatorConstraint, MultipartFile> {
    private static final List<String> contentTypes = Arrays.asList("png", "jpeg", "gif");
    
    @Override
    public void initialize(FileFormatValidatorConstraint contactNumber) {
    }
    
    @Override
    public boolean isValid(MultipartFile f, ConstraintValidatorContext constraintValidatorContext) {
        if (f.getSize() > 0) {
            return f.getContentType() != null && f.getContentType().length() > 0
                    && contentTypes.contains(f.getContentType().split("/")[1]);
        }
        return true;
        
        
    }
    
}
