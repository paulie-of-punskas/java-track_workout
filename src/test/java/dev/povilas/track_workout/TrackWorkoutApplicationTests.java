package dev.povilas.track_workout;

import dev.povilas.track_workout.model.Training;
import dev.povilas.track_workout.repository.Repository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrackWorkoutApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("getAllTrainings() returns non Null object")
	void returnNotNull() {
		var repository = new Repository();
		assertNotNull(repository.getAllTrainings());
	}

	@Test
	@DisplayName("Create training, but do not submit it.")
	void trainingNotAvailable() {
		var testTraining = new Training(LocalDateTime.parse("2020-12-31T00:00:00"),
				"",
				"exercise",
				0,
				0,
				0,
				0,
				0,
				"Labas is CLI");

		var repository = new Repository();
		assertFalse(repository.isTrainingInDB(testTraining));
	}

	@Test
	@DisplayName("Create training, submit it.")
	void submittedTrainingIsAvailable() {
		Repository repository = new Repository();
		Training testTraining = new Training(LocalDateTime.parse("2025-03-10T08:30:00"), "abs", "crunch", 0, 3, 0, 45, 0, "");
		assertTrue(repository.isTrainingInDB(testTraining));
	}
}
