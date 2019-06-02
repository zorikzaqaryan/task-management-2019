package com.example.task.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Role dto
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RoleDto {

    private Integer id;

    private String name;

    private List<UserDto> users;

}