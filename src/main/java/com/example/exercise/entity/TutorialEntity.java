package com.example.exercise.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tutorial")
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TutorialEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    String description;

    @Column (name = "status")
    boolean status;
}
