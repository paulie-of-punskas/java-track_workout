package dev.povilas.track_workout.repository;

import dev.povilas.track_workout.model.Training;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

@org.springframework.stereotype.Repository
public class Repository {

    String url = System.getenv("AZURE_DB_URL");
    String userName = System.getenv("AZURE_DB_USER");
    String password = System.getenv("AZURE_DB_PASSWORD");

    public boolean isTrainingInDB(Training training) {
        List<Training> allTrainings = getAllTrainings();
        for (int j = 0; j < allTrainings.size(); j++) {
            if (allTrainings.get(j).time().equals(training.time())) {
                return true;
            }
        }
        return false;
    }

    public void addTraining(Training training) {
        String query = "INSERT INTO workouts (date, muscle, exercise, kg, rep, distance, time, cal, comment) " +
                "VALUES ('"
                + training.date() + "', '"
                + training.muscle() + "', '"
                + training.exercise() + "', '"
                + training.kg() + "', '"
                + training.rep() + "', '"
                + training.distance() + "', '"
                + training.time() + "', '"
                + training.cal() + "', '"
                + training.comment() +
                "')";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement();) {
            int queryStatus = statement.executeUpdate(query);
            if (queryStatus > 0) {
                System.out.println(">> Records were added.");
            } else {
                System.out.println(">> Records could not be added.");
            }
        } catch (SQLException e) {
            System.out.println(">> SQLException occurred in addTraining()");
            System.out.println(">>   " + e.getMessage());
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
        String query = String.valueOf("INSERT INTO " +
                System.getenv("AZURE_TABLE_NAME") + " (date, muscle, exercise, kg, rep, distance, time, cal, comment) " +
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
                System.out.println(">> Records were added.");
            } else {
                System.out.println(">> Records could not be added.");
            }
        } catch (SQLException e) {
            System.out.println(">> SQLException occurred in addTraining()");
            System.out.println(">>   " + e.getMessage());
        }
    }

    public List<Training> getAllTrainings() {
        List<Training> trainingsList = new ArrayList<>();
        String query = "SELECT * FROM " + System.getenv("AZURE_TABLE_NAME");
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

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
            System.out.println(">> SQL Exception occurred in getAllTrainings()");
            System.out.println(">>   " + e.getMessage());
        }
        return trainingsList;
    };
}
