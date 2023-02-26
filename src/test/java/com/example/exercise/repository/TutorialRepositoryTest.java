package com.example.exercise.repository;

import com.example.exercise.entity.TutorialEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class TutorialRepositoryTest {

    @Mock
    TutorialRepository tutorialRepository;

//    @BeforeEach
//    void addTutorial() {
//        when(tutorialRepository.findById(1L)).thenReturn(Optional.of(new Tutorial(1L, null, null, false)));
//    }

    @AfterEach
    void tearDown() {
        tutorialRepository.deleteAll();
    }

    @Test
    void findAll() {
        List<TutorialEntity> tutorialList = new ArrayList<>();
        TutorialEntity tutorial1 = new TutorialEntity(1L, "title1", "description1", false);
        TutorialEntity tutorial2 = new TutorialEntity(2L, "title2", "description2", false);
        TutorialEntity tutorial3 = new TutorialEntity(3L, "title3", "description3", false);
        tutorialList.add(tutorial1);
        tutorialList.add(tutorial2);
        tutorialList.add(tutorial3);
        when(tutorialRepository.findAll()).thenReturn(tutorialList);

        List<TutorialEntity> newTutorialList = tutorialRepository.findAll();
        Assertions.assertFalse(newTutorialList.isEmpty());
        Assertions.assertTrue(newTutorialList.size() == 3);
    }

    @Test
    void deleteAll() {
        doNothing().when(tutorialRepository).deleteAll();
        tutorialRepository.deleteAll();
        verify(tutorialRepository, times(1)).deleteAll();
    }

    @Test
    void findById() {
        when(tutorialRepository.findById(1L)).thenReturn(Optional.of(new TutorialEntity(1L, "title1", "des1", false)));
        TutorialEntity tutorial = tutorialRepository.findById(1L).get();
        Assertions.assertNotNull(tutorial);
    }

    @Test
    void deleteById() {
        doNothing().when(tutorialRepository).deleteById(2L);
        tutorialRepository.deleteById(2L);
        verify(tutorialRepository, times(1)).deleteById(2L);
    }

    @Test
    void findByPublished() {
        List<TutorialEntity> tutorialList = new ArrayList<>();
        when(tutorialRepository.findByPublished(true)).thenReturn(tutorialList);

        //Case 1
        Assertions.assertTrue(tutorialRepository.findByPublished(true).isEmpty());

        //Case 2
        TutorialEntity tutorial1 = new TutorialEntity(1L, "title1", "description1", true);
        TutorialEntity tutorial2 = new TutorialEntity(2L, "title2", "description2", true);
        tutorialList.add(tutorial1);
        tutorialList.add(tutorial2);

        List<TutorialEntity> result = tutorialRepository.findByPublished(true);
        result.stream().forEach(i -> {
            Assertions.assertTrue(i.getPublished() == true);
        });

    }

    @Test
    void findByTitleContaining() {
        List<TutorialEntity> tutorialList = new ArrayList<>();
        when(tutorialRepository.findByTitleLike("title1")).thenReturn(tutorialList);

        //Case 1
        Assertions.assertTrue(tutorialRepository.findByTitleLike("title1").isEmpty());

        //Case 2
        TutorialEntity tutorial1 = new TutorialEntity(1L, "title12", "description1", true);
        TutorialEntity tutorial2 = new TutorialEntity(2L, "title122", "description2", true);
        tutorialList.add(tutorial1);
        tutorialList.add(tutorial2);

        List<TutorialEntity> result = tutorialRepository.findByTitleLike("title1");
        Assertions.assertTrue(result.size() == 2);
        result.stream().forEach(i -> {
            Assertions.assertTrue(i.getTitle().contains("title1"));
        });
    }

    @Test
    void save() {
        TutorialEntity tutorial = new TutorialEntity(1L, "title1", "des1", false);

        when(tutorialRepository.save(tutorial)).thenReturn(tutorial);

        TutorialEntity tutorial1 = tutorialRepository.save(tutorial);

        Assertions.assertNotNull(tutorial1);
        Assertions.assertTrue(Objects.equals(tutorial1.getId(), tutorial.getId()));
        Assertions.assertTrue(tutorial1.getTitle().equals(tutorial.getTitle()));
        Assertions.assertTrue(tutorial1.getDescription().equals(tutorial.getDescription()));
        Assertions.assertTrue(Objects.equals(tutorial1.getPublished(), tutorial.getPublished()));
    }
}