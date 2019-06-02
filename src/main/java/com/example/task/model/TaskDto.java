package com.example.task.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * task dto
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TaskDto {
    private Integer id;
    private String content;
    private String username;
    private String email;
    private byte[] image;
    private Boolean edited;

}