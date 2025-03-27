package dev.povilas.track_workout;

import dev.povilas.track_workout.repository.DBUtilities;
import dev.povilas.track_workout.repository.Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TrackWorkoutApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TrackWorkoutApplication.class, args);

		DBUtilities dbUtils = context.getBean(DBUtilities.class, "DBUtilities");

		Repository repository = context.getBean(Repository.class, "Repository");


//		 print all Beans
//		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}

}
