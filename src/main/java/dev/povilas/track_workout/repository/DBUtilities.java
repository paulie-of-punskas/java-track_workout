package dev.povilas.track_workout.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import java.sql.*;
import java.util.logging.Logger;

@Configuration
public class DBUtilities {

    protected final Logger logger = Logger.getLogger(Repository.class.getName());

    @Bean
    public boolean testDBconnection(@Value("${db.url}") String url,
                                    @Value("${db.user}") String user,
                                    @Value("${db.password}") String password) {

        String query = "SELECT * FROM track_workout";
        int retryCounter = 1;

        logger.info("DBUtilities.testDBconnection(): Connecting to the database.");

        while (retryCounter <= 4) {
            try (Connection connection = DriverManager.getConnection(url, user, password);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnsNumber = metaData.getColumnCount();
                if (columnsNumber > 0) {
                    logger.info("DBUtilities.testDBconnection(): Attempt: " + retryCounter + ". Connection was successful. Closing it.");
                    connection.close();
                    return true;
                }
            } catch (SQLException e) {
                logger.info("DBUtilities.testDBconnection(): Attempt: " + retryCounter +", failed. Could not establish DB Connection.");
                logger.info("DBUtilities.testDBconnection(): SQLException occurred: " + e.getMessage());
                logger.info("DBUtilities.testDBconnection(): Error Code: " + e.getErrorCode());
                if (e.getErrorCode() == 40613) {
                    retryCounter = retryCounter + 1;
                } else {
                    retryCounter = 5;
                }
            }
        }
        return false;
    }
}
