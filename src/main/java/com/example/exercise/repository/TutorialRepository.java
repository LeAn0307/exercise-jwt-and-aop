package com.example.exercise.repository;

import com.example.exercise.entity.TutorialEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<TutorialEntity,Long> {

    List<TutorialEntity> findAll();
    List<TutorialEntity> findByPublished(boolean published);
     List<TutorialEntity> findByTitleLike(String title);
}
