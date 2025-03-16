package dev.povilas.track_workout.repository;

import dev.povilas.track_workout.model.Training;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class Repository {

    String url = "jdbc:h2:mem:db_workouts";
    String userName = "sa";
    String password = "";


    public void isTrainingInDB() {
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
        String query = "INSERT INTO workouts (date, muscle, exercise, kg, rep, distance, time, cal, comment) " +
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

    public List<Training> readAllTrainings() {
        List<Training> trainingsList = new ArrayList<>();
        String query = "SELECT * FROM workouts";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Training training = new Training(resultSet.getObject(1, LocalDateTime.class),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getString(9));
                trainingsList.add(training);
            }
        } catch (SQLException e) {
            System.out.println(">> SQL Exception occurred in readAllTrainings()");
            System.out.println(">>   " + e.getMessage());
        }
        return trainingsList;
    };
}
