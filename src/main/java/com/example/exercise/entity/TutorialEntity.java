package com.example.exercise.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tutorial")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TutorialEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    String description;

    @Column (name = "published")
    boolean published;



    public boolean getPublished() {
        return published;
    }
}