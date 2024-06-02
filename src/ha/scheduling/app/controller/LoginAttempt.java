package ha.scheduling.app.controller;

import ha.scheduling.app.JdbcConnection;
import ha.scheduling.app.model.User;
import java.sql.*;

/**
 * Provides a way to login to the system.
 */
public class LoginAttempt {

    /**
     * Attempts to login to the system with the specified username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The user object if the login was successful, or null if the login
     * failed.
     * @throws SQLException If an SQL error occurs.
     */
    public static User login(String username, String password) throws
            SQLException {

        try (Connection conn = JdbcConnection.openConnection()) {
            // Prepare the SQL query to select the user by username and password
            String sql = "SELECT * FROM users WHERE User_Name=? AND Password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute the query and get the result set
            ResultSet rs = stmt.executeQuery();

            // Check if the user was found
            if (rs.next()) {
                // Create a new User object with the data from the result set
                User user = new User();
                user.setUserId(rs.getInt("User_ID"));
                user.setUserName(rs.getString("User_Name"));
                user.setPassword(rs.getString("Password"));
                user.setCreateDate(rs.getTimestamp("Create_Date").toLocalDateTime());
                user.setCreatedBy(rs.getString("Created_By"));
                user.setLastUpdate(rs.getTimestamp("Last_Update").toLocalDateTime());
                user.setLastUpdatedBy(rs.getString("Last_Updated_By"));

                // Return the User object
                return user;
            } else {
                // User not found, return null
                return null;
            }
        }
    }
}
