package com.example.exercise.service.Impl;

import com.example.exercise.entity.TutorialEntity;
import com.example.exercise.repository.TutorialRepository;
import com.example.exercise.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialServiceImpl implements TutorialService {
    @Autowired
    TutorialRepository tutorialRepository;

    @Override
    public List<TutorialEntity> findAll() {
        return tutorialRepository.findAll();
    }

    @Override
    public List<TutorialEntity> findByTitleLike(String title) {
        return tutorialRepository.findByTitleLike(title);
    }

    @Override
    public TutorialEntity findById(long id) {
        return tutorialRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(long id) {
        tutorialRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
       tutorialRepository.deleteAll();
    }

    @Override
    public List<TutorialEntity> findByPublished(boolean b) {
        return tutorialRepository.findByPublished(true);
    }

    @Override
    public TutorialEntity saveTutorial(TutorialEntity tutorial) {
        return tutorialRepository.save(tutorial);
    }


}
