package dev.povilas.track_workout;

import dev.povilas.track_workout.model.Training;
import dev.povilas.track_workout.repository.DBUtilities;
import dev.povilas.track_workout.repository.Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.List;

import java.sql.*;

@SpringBootApplication
public class TrackWorkoutApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TrackWorkoutApplication.class, args);

		DBUtilities dbUtils = context.getBean(DBUtilities.class, "DBUtilities");

		dbUtils.testDBconnection(System.getenv("AZURE_DB_URL"),
				System.getenv("AZURE_DB_USER"),
				System.getenv("AZURE_DB_PASSWORD"));

		Repository repository = context.getBean(Repository.class, "Repository");

		Training tr = new Training("2025-03-20T00:00:00", "kojos", "dviratis", 0.00, 1, 16, 45, 259, "16 km");
		System.out.println(repository.isTrainingInDB(tr));

		List<Training> trainings = repository.getAllTrainings();

		for (Training training : trainings) {
			System.out.println(training.date());
		}


//		repository.addTraining(tr);

//		int counter = 0;
//		Connection con = null;
//		while(counter < 3 && con == null){
//			try {
//				String str = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//				Class.forName(str).newInstance();
//				con = DriverManager.getConnection("jdbc:sqlserver://trackworkout.database.windows.net:1433;database=db-trackworkout;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", "pbernecki@protonmail.com@trackworkout", "nUthog-myjnu0-jiznyq");
//				System.out.println("Attempt: " + counter + ", DB Connection Successful!");
//			} catch (SQLException e) {
//				if(e.getErrorCode() == 0 || e.getErrorCode() == 4060)
//					counter++;
//				System.out.println("Attempt: " + counter +", Could not establish DB Connection!");
//				System.out.println("Error Code: " + e.getErrorCode());
//
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				if(con != null){
//					try {
//						con.close();
//						System.out.println("Connection closed...");
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}

//		repository.addTraining(tr);
//		System.out.println(repository.isTrainingInDB(tr));

//		// sitas veikia, jeigu addTraining() nepriema objekto
//		repository.addTraining(LocalDateTime.parse("2025-03-10T08:30:00"), "abs", "crunch", 0, 3, 0, 45, 0, "");
//
//		// sukuriam objekta ir paduodam ji i addTraining()
//		Training newTraining = new Training(LocalDateTime.now(), "abs", "crunch", 0, 3, 0, 45, 0, "training object");
//		repository.addTraining(newTraining);

//		DBUtilities utils = new DBUtilities();
//		utils.testDBconnection(System.getenv("AZURE_DB_URL"), System.getenv("AZURE_DB_USER"), System.getenv("AZURE_DB_PASSWORD"));

//		 print all Beans
//		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}

}
