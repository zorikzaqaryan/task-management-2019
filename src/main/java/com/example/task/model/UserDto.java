package com.example.task.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * User dto
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private List<RoleDto> roles;
}