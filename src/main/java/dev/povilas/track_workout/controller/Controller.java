package dev.povilas.track_workout.controller;

import dev.povilas.track_workout.model.Training;
import dev.povilas.track_workout.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
        return repository.readAllTrainings();
    }

    @GetMapping("/submit_training")
    public String submitWorkout(LocalDateTime date,
                                String muscle,
                                String exercise,
                                Integer kg,
                                Integer rep,
                                Integer distance,
                                Integer time,
                                Integer cal,
                                String comment) {
        repository.addTraining(date, muscle, exercise, kg, rep, distance, time, cal, comment);
//        If Training object was created, and it exists in DB, then return
        return "Training was submitted";
    }
}
