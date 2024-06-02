package ha.scheduling.app;

import java.sql.*;

/**
 * Provides a way to open and close a database connection.
 */
public class JdbcConnection {

    /**
     * The database connection.
     */
    private static Connection connection;
    /**
     * The database configuration.
     */
    private static final DatabaseConfig CONFIG = new DatabaseConfig();

    /**
     * Opens a database connection.
     *
     * @return The database connection.
     */
    public static Connection openConnection() {
        try {
            connection = DriverManager.getConnection(CONFIG.getUrl(), CONFIG.getUsername(), CONFIG.getPassword());
            System.out.println("Connection successful!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }

        return null;
    }

    /**
     * Closes a database connection.
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
