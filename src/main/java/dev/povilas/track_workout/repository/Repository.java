package dev.povilas.track_workout.repository;

import dev.povilas.track_workout.model.Training;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@org.springframework.stereotype.Repository
public class Repository {

    protected final Logger logger = LoggerFactory.getLogger(Repository.class.getName());
//    protected final Logger logger = Logger.getLogger(Repository.class.getName());

    String url = System.getenv("AZURE_DB_URL");
    String userName = System.getenv("AZURE_DB_USER");
    String password = System.getenv("AZURE_DB_PASSWORD");

    public boolean isTrainingInDB(Training training) {
        List<Training> allTrainings = getAllTrainings();
        for (Training allTraining : allTrainings) {
            if (allTraining.date().equals(training.date())) {
                return true;
            }
        }
        return false;
    }

    public void addTraining(Training training) {
        String query = "INSERT INTO " + System.getenv("AZURE_TABLE_NAME") + " (date, muscle, exercise, kg, rep, comment, distance, time, cal) " +
                "VALUES ('"
                + training.date() + "', '"
                + training.muscle() + "', '"
                + training.exercise() + "', '"
                + training.kg() + "', '"
                + training.rep() + "', '"
                + training.comment() + "', '"
                + training.distance() + "', '"
                + training.time() + "', '"
                + training.cal() + "')";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement();) {
            int queryStatus = statement.executeUpdate(query);
            if (queryStatus > 0) {
                logger.info("Repository.addTraining(): Records were added.");
            } else {
                logger.info("Repository.addTraining(): Records could not be added.");
            }
        } catch (SQLException e) {
            logger.warn("Repository.addTraining(): SQLException occurred: `{}`", e.getMessage());
        }
    }

    public void addTraining(LocalDateTime date,
                            String muscle,
                            String exercise,
                            Integer kg,
                            Integer rep,
                            Integer distance,
                            Integer time,
                            Integer cal,
                            String comment) {

        String query = ("INSERT INTO " +
                System.getenv("AZURE_TABLE_NAME") + " (date, muscle, exercise, kg, rep, comment, distance, time, cal) " +
                "VALUES ('"
                + date + "', '"
                + muscle + "', '"
                + exercise + "', '"
                + kg + "', '"
                + rep + "', '"
                + distance + "', '"
                + time + "', '"
                + cal + "', '"
                + comment +
                "')");

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement();) {
            int queryStatus = statement.executeUpdate(query);
            if (queryStatus > 0) {
                logger.info("Repository.addTraining(): Records were added.");
            } else {
                logger.info("Repository.addTraining(): Records could not be added.");
            }
        } catch (SQLException e) {
            logger.info("Repository.addTraining(): SQLException occurred: " + e.getMessage());
        }
    }

    public List<Training> getAllTrainings() {
        List<Training> trainingsList = new ArrayList<>();
        String query = "SELECT * FROM " + System.getenv("AZURE_TABLE_NAME");
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            logger.info("Repository.getAllTrainings(): Fetching all trainings from DB.");

            while (resultSet.next()) {
                Training training = new Training(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5),
                        resultSet.getInt(9),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getString(6));
                trainingsList.add(training);
            }
        } catch (SQLException e) {
            logger.info("Repository.getAllTrainings(): SQLException occurred: " + e.getMessage());
        }
        return trainingsList;
    };
}
