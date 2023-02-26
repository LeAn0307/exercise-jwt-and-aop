package com.example.exercise.controller;

import com.example.exercise.entity.TutorialEntity;
import com.example.exercise.repository.TutorialRepository;
import com.example.exercise.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    private TutorialService tutorialService;

    // Get all & get by title
    // GET /api/tutorials
    // GET /api/tutorials ? title =[keyword]
    @GetMapping("tutorials")
    public ResponseEntity<List<TutorialEntity>> getListTutorials(@RequestParam(required = false)String title){

      try{
          if (tutorialService.findByTitleLike(title).isEmpty() || tutorialService.findAll().isEmpty())
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);

          return title != null
                  ? new ResponseEntity( tutorialService.findByTitleLike(title), HttpStatus.OK)
                  : new ResponseEntity( tutorialService.findAll(), HttpStatus.OK);

      }
      catch (Exception ex){
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }

   // create new tutorial
   // POST /api/tutorials
    @PostMapping("/tutorial")
    public ResponseEntity<TutorialEntity> createNewTutorial( @RequestBody TutorialEntity tutorial)
    {
        try {
            return new ResponseEntity<>(tutorialService.saveTutorial(tutorial), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Get by id
    // GET /tutorials/{id}

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<TutorialEntity> getTutorialById(@PathVariable Long id) {
        try {
            return tutorialService.findById(id) != null
                    ? new ResponseEntity(tutorialService.findById(id), HttpStatus.OK)
                    :  new ResponseEntity(HttpStatus.NOT_FOUND);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    edit 1 tutorial
//    PUT /tutorials/{id}

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<TutorialEntity> updateTutorial(@RequestBody TutorialEntity tutorial, @PathVariable Long id) {
        try {
            TutorialEntity newTutorial = tutorialService.findById(id);
            if(newTutorial == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                newTutorial.setTitle(tutorial.getTitle());
                newTutorial.setDescription(tutorial.getDescription());
                newTutorial.setPublished(tutorial.getPublished());
                return new ResponseEntity<>(tutorialService.saveTutorial(newTutorial), HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete by id
    // Delete /tutorials/{id}
    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity deleteTutorial(@PathVariable Long id) {
        try {
            tutorialService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete All
    // Delete /tutorials
    @DeleteMapping("/tutorials")
    public ResponseEntity deleteAllTutorials() {
        try {
            tutorialService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/tutorials/published")
    public ResponseEntity<List<TutorialEntity>> getTutorialsPublished() {

        try {
            List<TutorialEntity> tutorialList = tutorialService.findByPublished(true);

            return tutorialList.isEmpty()
                    ? new ResponseEntity(HttpStatus.NO_CONTENT)
                    : new ResponseEntity<>(tutorialList, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
