package com.example.exercise.controller;

import com.example.exercise.entity.TutorialEntity;
import com.example.exercise.service.TutorialService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TutorialController.class)
class TutorialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TutorialService tutorialService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTutorialById() throws Exception {
        TutorialEntity responseTutorials = new TutorialEntity(1L, "title1", "des1", true);
        when(tutorialService.findById(1L)).thenReturn(responseTutorials);

        //Case 1:
        mockMvc.perform(get("/api/tutorials/2"))
                .andExpect(status().isNotFound());

        //Case 2:
        mockMvc.perform(get("/api/tutorials/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("title1"))
                .andExpect(jsonPath("$.description").value("des1"))
                .andExpect(jsonPath("$.published").value(true));
    }

    @Test
    void updateTutorial() throws Exception {
        TutorialEntity existTutorials = new TutorialEntity(1L, "title1", "des1", true);
        TutorialEntity updatedTutorials = new TutorialEntity(1L, "updated title1", "updated des1", false);
        when(tutorialService.findById(1L)).thenReturn(existTutorials);
        when(tutorialService.saveTutorial(any(TutorialEntity.class))).thenReturn(updatedTutorials);

        //Case 1:
        mockMvc.perform(put("/api/tutorials/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"updated title1\", \"description\": \"updated des1\", \"published\": false}"))
                .andExpect(status().isNotFound());

        //Case 2:
        mockMvc.perform(put("/api/tutorials/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"updated title1\", \"description\": \"updated des1\", \"published\": false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("updated title1"))
                .andExpect(jsonPath("$.description").value("updated des1"))
                .andExpect(jsonPath("$.published").value(false));
    }

    @Test
    void deleteTutorial() throws Exception {
        doNothing().when(tutorialService).deleteById(1L);

        mockMvc.perform(delete("/api/tutorials/1"))
                .andExpect(status().isOk());

        verify(tutorialService, times(1)).deleteById(1L);
    }

    @Test
    void deleteAllTutorials() throws Exception {
        doNothing().when(tutorialService).deleteAll();

        mockMvc.perform(delete("/api/tutorials"))
                .andExpect(status().isOk());

        verify(tutorialService, times(1)).deleteAll();
    }

    @Test
    void getTutorialsPublished() throws Exception {
        List<TutorialEntity> tutorialList = new ArrayList<>();
        when(tutorialService.findByPublished(true)).thenReturn(tutorialList);

        //Case 1:
        mockMvc.perform(get("/api/tutorials/published"))
                .andExpect(status().isNoContent());

        //Case 2:
        TutorialEntity tutorial1 = new TutorialEntity(1L, "title1", "description1", true);
        TutorialEntity tutorial2 = new TutorialEntity(2L, "title2", "description2", true);
        tutorialList.add(tutorial1);
        tutorialList.add(tutorial2);

        mockMvc.perform(get("/api/tutorials/published"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].published", is(true)))
                .andExpect(jsonPath("$[1].published", is(true)));
    }
}