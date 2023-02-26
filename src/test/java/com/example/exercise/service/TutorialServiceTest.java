package com.example.exercise.service;

import com.example.exercise.entity.TutorialEntity;
import com.example.exercise.entity.TutorialEntity;
import com.example.exercise.repository.TutorialRepository;
import com.example.exercise.service.Impl.TutorialServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class TutorialEntityServiceTest {

    @Mock
    TutorialRepository tutorialRepository;

    @InjectMocks
    TutorialServiceImpl tutorialService;

    @BeforeEach
    void setUp() {
        List<TutorialEntity> tutorialList = new ArrayList<>();
        TutorialEntity tutorial1 = new TutorialEntity(1L, "title1", "description1", false);
        TutorialEntity tutorial2 = new TutorialEntity(2L, "title2", "description2", false);
        TutorialEntity tutorial3 = new TutorialEntity(3L, "title3", "description3", false);
        tutorialList.add(tutorial1);
        tutorialList.add(tutorial2);
        tutorialList.add(tutorial3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        List<TutorialEntity> tutorialList = new ArrayList<>();
        when(tutorialRepository.findAll()).thenReturn(tutorialList);

        //Case 1
        Assertions.assertTrue(tutorialService.findAll().isEmpty());

        //Case 2
        TutorialEntity tutorial1 = new TutorialEntity(1L, "title1", "description1", false);
        TutorialEntity tutorial2 = new TutorialEntity(2L, "title2", "description2", false);
        tutorialList.add(tutorial1);
        tutorialList.add(tutorial2);

        List<TutorialEntity> tutorials = tutorialService.findAll();

        Assertions.assertFalse(tutorials.isEmpty());
        Assertions.assertTrue(tutorials.size() == 2);
    }

    @Test
    void findById() {
        TutorialEntity tutorial = new TutorialEntity(1L, "title1", "des1", false);
        when(tutorialRepository.findById(1L)).thenReturn(Optional.of(tutorial));

        TutorialEntity result = tutorialService.findById(1L);

        Assertions.assertTrue(Objects.equals(result.getId(), tutorial.getId()));
    }

    @Test
    void deleteAll() {
        doNothing().when(tutorialRepository).deleteAll();
        tutorialService.deleteAll();
        verify(tutorialRepository, times(1)).deleteAll();
    }

    @Test
    void deleteById() {
        doNothing().when(tutorialRepository).deleteById(1L);
        tutorialService.deleteById(1L);
        verify(tutorialRepository, times(1)).deleteById(1L);
    }

    @Test
    void findByPublished() {
        List<TutorialEntity> tutorialList = new ArrayList<>();
        when(tutorialRepository.findByPublished(true)).thenReturn(tutorialList);

        //Case 1
        Assertions.assertTrue(tutorialService.findByPublished(true).isEmpty());

        //Case 2
        TutorialEntity tutorial1 = new TutorialEntity(1L, "title1", "description1", true);
        TutorialEntity tutorial2 = new TutorialEntity(2L, "title2", "description2", true);
        tutorialList.add(tutorial1);
        tutorialList.add(tutorial2);

        List<TutorialEntity> result = tutorialService.findByPublished(true);
        result.stream().forEach(i -> {
            Assertions.assertTrue(i.getPublished() == true);
        });
    }

    @Test
    void findByTitleContain() {
        List<TutorialEntity> tutorialList = new ArrayList<>();
        when(tutorialRepository.findByTitleLike("title1")).thenReturn(tutorialList);

        //Case 1
        Assertions.assertTrue(tutorialService.findByTitleLike("title1").isEmpty());

        //Case 2
        TutorialEntity tutorial1 = new TutorialEntity(1L, "title12", "description1", true);
        TutorialEntity tutorial2 = new TutorialEntity(2L, "title122", "description2", true);
        tutorialList.add(tutorial1);
        tutorialList.add(tutorial2);

        List<TutorialEntity> result = tutorialService.findByTitleLike("title1");
        Assertions.assertTrue(result.size() == 2);
        result.stream().forEach(i -> {
            Assertions.assertTrue(i.getTitle().contains("title1"));
        });
    }

    @Test
    void save() {
        TutorialEntity tutorial = new TutorialEntity(1L, "title1", "des1", false);

        when(tutorialRepository.save(tutorial)).thenReturn(tutorial);

        TutorialEntity tutorial1 = tutorialService.saveTutorial(tutorial);

        Assertions.assertNotNull(tutorial1);
        Assertions.assertTrue(Objects.equals(tutorial1.getId(), tutorial.getId()));
        Assertions.assertTrue(tutorial1.getTitle().equals(tutorial.getTitle()));
        Assertions.assertTrue(tutorial1.getDescription().equals(tutorial.getDescription()));
        Assertions.assertTrue(Objects.equals(tutorial1.getPublished(), tutorial.getPublished()));
    }
}