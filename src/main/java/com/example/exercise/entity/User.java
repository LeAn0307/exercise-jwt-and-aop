package com.example.exercise.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_db")
@Data // lombok
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
}