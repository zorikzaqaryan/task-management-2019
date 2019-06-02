package com.example.task.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * For Task
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PreviewCmd {
    private Integer id;
    
    private String content;
    
    private String username;
 
    private String email;
 
}