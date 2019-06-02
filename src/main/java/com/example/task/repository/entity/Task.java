package com.example.task.repository.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * task entity
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(nullable = false)
    private String content;
    
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String email;
    
    @Column
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean edited;
    
    @Lob
    private byte[] image;

}