package dev.povilas.track_workout.controller;

import dev.povilas.track_workout.model.Training;
import dev.povilas.track_workout.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    Repository repository;

    @GetMapping("/")
    public void index() {
        System.out.println("Kazkas atejo");
    }

    @GetMapping("/labas")
    public String labas() {
        return "Labas";
    }

    @GetMapping("/get_all_trainings")
    public List<Training> getAllWorkouts() {
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
