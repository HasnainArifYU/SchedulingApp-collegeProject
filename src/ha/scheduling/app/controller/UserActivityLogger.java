package ha.scheduling.app.controller;

import java.io.*;
import java.time.LocalDateTime;

/**
 * A class that logs user login activity.
 */
public class UserActivityLogger {

    /**
     * The path to the log file.
     */
    private static final String LOG_FILE_PATH = "login_activity.txt";

    /**
     * Logs a login attempt.
     *
     * @param username The username of the user.
     * @param success Whether the login was successful.
     */
    public static void logLoginAttempt(String username, boolean success) {
        String logEntry = createLogEntry(username, success);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Creates a log entry for a login attempt.
     *
     * @param username The username of the user.
     * @param success Whether the login was successful.
     * @return The log entry.
     */
    private static String createLogEntry(String username, boolean success) {
        LocalDateTime timestamp = LocalDateTime.now();
        String status = success ? "SUCCESS" : "FAILURE";
        return String.format("[%s] User: %s - %s", timestamp.toString(), username, status);
    }
}
