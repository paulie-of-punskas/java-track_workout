package dev.povilas.track_workout;

import dev.povilas.track_workout.model.Training;
import dev.povilas.track_workout.repository.DBUtilities;
import dev.povilas.track_workout.repository.Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.*;

@SpringBootApplication
public class TrackWorkoutApplication {

	public static void main(String[] args) throws SQLException {
		ConfigurableApplicationContext context = SpringApplication.run(TrackWorkoutApplication.class, args);

		Repository repository = new Repository();
		System.out.println(repository.getAllTrainings());

//		// sitas veikia, jeigu addTraining() nepriema objekto
//		repository.addTraining(LocalDateTime.parse("2025-03-10T08:30:00"), "abs", "crunch", 0, 3, 0, 45, 0, "");
//
//		// sukuriam objekta ir paduodam ji i addTraining()
//		Training newTraining = new Training(LocalDateTime.now(), "abs", "crunch", 0, 3, 0, 45, 0, "training object");
//		repository.addTraining(newTraining);

		DBUtilities utils = new DBUtilities();
		utils.testDBconnection(System.getenv("AZURE_DB_URL"), System.getenv("AZURE_DB_USER"), System.getenv("AZURE_DB_PASSWORD"));

//		 print all Beans
//		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}

}
