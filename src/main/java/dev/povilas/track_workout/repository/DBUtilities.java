package dev.povilas.track_workout.repository;

import java.sql.*;

public class DBUtilities extends Repository {
    public boolean testDBconnection(String url, String user, String password) {
        java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
        logger.info("Connecting to the database.");
        String query = "SELECT * FROM track_workout";

        try (Connection connection = DriverManager.getConnection(url = url, user = user, password = password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnsNumber = metaData.getColumnCount();
            if (columnsNumber > 0) {
                logger.info("Connection was successful. Closing the connection.");
                connection.close();
                return true;
            }
        } catch (SQLException e) {
            logger.severe("SQL Exception occurred: " + e.getMessage());
        }
        return false;
    }
}
