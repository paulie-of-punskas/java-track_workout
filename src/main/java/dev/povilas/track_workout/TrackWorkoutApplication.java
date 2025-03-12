package dev.povilas.track_workout;

import dev.povilas.track_workout.model.Training;
import dev.povilas.track_workout.repository.Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TrackWorkoutApplication {

	public static void main(String[] args) throws SQLException {
		ConfigurableApplicationContext context = SpringApplication.run(TrackWorkoutApplication.class, args);

		Repository repository = new Repository();
		System.out.println(repository.readAllTrainings());

		repository.addTraining(LocalDateTime.parse("2025-03-10T08:30:00"), "abs", "crunch", 0, 3, 0, 45, 0, "");

//		List<Training> trainings = repository.readAllTrainings();
//		System.out.println(trainings);

//		for (int j = 0; j < trainingsList.size(); j++) {
//			System.out.println(trainingsList.get(j));
//		}

		// print all Beans
//		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}

}
