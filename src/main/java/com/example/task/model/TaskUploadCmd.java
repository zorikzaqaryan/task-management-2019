package com.example.task.model;

import com.example.task.validator.FileFormatValidatorConstraint;
import com.example.task.validator.FileSizeValidatorConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * For Task upload
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TaskUploadCmd {
    private Integer id;
    
    @NotEmpty
    private String content;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    @Email
    private String email;
    
    private Boolean edited;
    
    @FileSizeValidatorConstraint
    @FileFormatValidatorConstraint
    private MultipartFile file;
}