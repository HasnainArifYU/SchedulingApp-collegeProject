package ha.scheduling.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Provides a way to configure a database connection.
 */
public class DatabaseConfig {

    /**
     * A {@link Properties} object that contains the database connection
     * information.
     */
    private Properties properties;

    /**
     * The constructor for the {@code DatabaseConfig} class.
     */
    public DatabaseConfig() {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream("db-config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
    }

    /**
     * Gets the URL of the database.
     *
     * @return The URL of the database.
     */
    public String getUrl() {
        return properties.getProperty("db.url");
    }

    /**
     * Gets the username for the database connection.
     *
     * @return The username for the database connection.
     */
    public String getUsername() {
        return properties.getProperty("db.username");
    }

    /**
     * Gets the password for the database connection.
     *
     * @return The password for the database connection.
     */
    public String getPassword() {
        return properties.getProperty("db.password");
    }

}
