package dev.povilas.track_workout.controller;

import dev.povilas.track_workout.model.Training;
import dev.povilas.track_workout.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    Repository repository;

    protected final Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/")
    public String index() {
        logger.info("Kazkas atejo");
        return "index";
    }

    @GetMapping("/labas")
    public String labas() {
        return "Labas";
    }

    @GetMapping("/get_all_trainings")
    public List<Training> returnAllWorkouts() {
        return repository.getAllTrainings();
    }

    @PostMapping("/submit_training")
    public String submitWorkout(@RequestBody Training training) {
        repository.addTraining(training);
        if (repository.isTrainingInDB(training)) {
            return "Training was submitted with great success.";
        }
        return "Training was not submitted.";
    }
}
