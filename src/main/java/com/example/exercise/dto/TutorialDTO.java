package com.example.exercise.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class TutorialDTO {
    private long id;
    private String title;
    String description;
    boolean published;
}
