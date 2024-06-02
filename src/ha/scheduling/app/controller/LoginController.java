package ha.scheduling.app.controller;

import ha.scheduling.app.JdbcConnection;
import ha.scheduling.app.model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorLabel;
    @FXML
    private Label locationLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ZoneId zoneId = ZoneId.systemDefault();
        String id = zoneId.getId();
        String zoneFullDisplayName = zoneId.getDisplayName(TextStyle.FULL, Locale.getDefault());
        String zoneShortDisplayName = zoneId.getDisplayName(TextStyle.SHORT, Locale.getDefault());
        locationLabel.setText(String.format("%s (%s - %s)", id, zoneFullDisplayName, zoneShortDisplayName));

        loginButton.setOnAction((event) -> handleLogin());
    }

    /**
     * This method handles the login of a user.
     */
    private void handleLogin() {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            errorLabel.setVisible(true);
        } else {
            User user = null;

            try {
                user = LoginAttempt.login(username, password);
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            boolean success = (user != null);

            // Log login activity
            UserActivityLogger.logLoginAttempt(username, success);

            if (success) {
                errorLabel.setVisible(false);
                openAppWindow(user);
            } else {
                errorLabel.setVisible(true);
            }
        }
    }

    /**
     * This method opens the main app window for the specified user.
     *
     * @param user The user who is logging in.
     */
    private void openAppWindow(User user) {
        // Load the FXML file for the app window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ha/scheduling/app/view/app-window.fxml"));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException ex) {
            System.out.println("Error loading app main window " + ex.getMessage());
        }

        if (root == null) {
            return;
        }

        String sql = "SELECT Appointment_ID, Start FROM appointments WHERE User_ID = ?";
        try (Connection conn = JdbcConnection.openConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            // Get the current user's ID
            statement.setInt(1, user.getUserId());

            try (ResultSet rs = statement.executeQuery()) {
                // Get the user's default time zone
                ZoneId userTimeZone = ZoneId.systemDefault();

                boolean upComingAppointment = false;

                while (rs.next()) {
                    // Get the UTC time from the database
                    Timestamp appointmentUtcTimestamp = rs.getTimestamp("Start");
                    // Convert the UTC time to the user's local time
                    LocalDateTime appointmentLocalDateTime = appointmentUtcTimestamp.toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(userTimeZone).toLocalDateTime();

                    // Check if the appointment is within 15 minutes of the user's log in
                    LocalDateTime now = LocalDateTime.now();
                    Duration duration = Duration.between(now, appointmentLocalDateTime);
                    long minutesDiff = duration.toMinutes();

                    System.out.printf("appointment: %s; now: %s; minutes difference: %d%n", appointmentLocalDateTime, now, minutesDiff);

                    if (minutesDiff > 0 && minutesDiff < 16) {
                        upComingAppointment = true;
                        // Display the alert with the appointment details
                        // If there is an upcoming appointment, show an alert with the appointment details
                        int appointmentId = rs.getInt("Appointment_ID");
                        LocalDate date = appointmentLocalDateTime.toLocalDate();
                        LocalTime time = appointmentLocalDateTime.toLocalTime();
                        String message = String.format("You have an upcoming appointment (ID: %d) on %s at %s", appointmentId, date, time);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
                        alert.setHeaderText("Upcoming Appointment");
                        alert.showAndWait();
                        break;
                    }
                }

                if (!upComingAppointment) {
                    // If there are no upcoming appointments, show a custom message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have no upcoming appointments within the next 15 minutes.");
                    alert.setHeaderText("Upcoming Appointment");
                    alert.showAndWait();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        // Set the user data on the main window controller
        AppWindowController controller = loader.getController();
        controller.setUser(user);

        // Create a new scene and set it on the primary stage
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        // Close the login window
        Stage loginStage = (Stage) loginButton.getScene().getWindow();
        loginStage.close();
    }

}
