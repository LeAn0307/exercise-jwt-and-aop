package com.example.exercise.service;

import com.example.exercise.entity.TutorialEntity;

import java.util.List;

public interface TutorialService{
    List<TutorialEntity> findAll();
    List<TutorialEntity> findByTitleCharacter(String title);
    TutorialEntity findById(long id);
    void deleteById(long id);
    void deleteAll();
    List<TutorialEntity> findByPublished(boolean b);
    TutorialEntity saveTutorial(TutorialEntity tutorial);

}
