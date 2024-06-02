package ha.scheduling.app.controller;

import ha.scheduling.app.JdbcConnection;
import ha.scheduling.app.model.*;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.IsoFields;
import java.util.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class AppWindowController implements Initializable {

    private User user;
    @FXML
    private VBox menuVBox;
    //Customers
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
    private final ObservableList<Country> customerCountries = FXCollections.observableArrayList();
    private final ObservableList<FirstLevelDivision> customerDivisions = FXCollections.observableArrayList();
    @FXML
    private Button customersRecordsMenuButton;
    @FXML
    private BorderPane customersRecordsPane;
    @FXML
    private Button backToMenuFromCustomersRecordsButton;
    @FXML
    private TableView<Customer> customersTable;
    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    @FXML
    private TableColumn<Customer, String> customerAddressColumn;
    @FXML
    private TableColumn<Customer, String> customerPostalCodeColumn;
    @FXML
    private TableColumn<Customer, String> customerPhoneColumn;
    @FXML
    private TableColumn<Customer, String> customerDivisionColumn;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField customerAddressField;
    @FXML
    private TextField customerPostalCodeField;
    @FXML
    private TextField customerPhoneField;
    @FXML
    private ComboBox<Country> customerCountryComboBox;
    @FXML
    private ComboBox<FirstLevelDivision> customerDivisionComboBox;
    @FXML
    private Button newCustomerButton;
    @FXML
    private Button saveCustomerButton;
    @FXML
    private Button deleteCustomerButton;
    // Appointments
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    private final ObservableList<Customer> appointmentCustomers = FXCollections.observableArrayList();
    private final ObservableList<User> appointmentUsers = FXCollections.observableArrayList();
    private final ObservableList<Contact> appointmentContacts = FXCollections.observableArrayList();
    private final ObservableList<Month> appointmentMonths = FXCollections.observableArrayList();
    private final ObservableList<String> appointmentWeeks = FXCollections.observableArrayList();
    @FXML
    private Button appointmentsMenuButton;
    @FXML
    private BorderPane appointmentsPane;
    @FXML
    private Button backToMenuFromAppointmentsButton;
    @FXML
    private RadioButton clearAppointmentsFilterRadio;
    @FXML
    private RadioButton filterAppointmentsByMonthRadio;
    @FXML
    private ComboBox<Month> appointmentMonthsComboBox;
    @FXML
    private RadioButton filterAppointmentsByWeekRadio;
    @FXML
    private ComboBox<String> appointmentWeeksComboBox;
    @FXML
    private TableView<Appointment> appointmentsTable;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdColumn;
    @FXML
    private TableColumn<Appointment, String> appointmentTitleColumn;
    @FXML
    private TableColumn<Appointment, String> appointmentDescriptionColumn;
    @FXML
    private TableColumn<Appointment, String> appointmentLocationColumn;
    @FXML
    private TableColumn<Appointment, String> appointmentContactColumn;
    @FXML
    private TableColumn<Appointment, String> appointmentTypeColumn;
    @FXML
    private TableColumn<Appointment, String> appointmentStartDateColumn;
    @FXML
    private TableColumn<Appointment, String> appointmentEndDateColumn;
    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomerIdColumn;
    @FXML
    private TableColumn<Appointment, Integer> appointmentUserIdColumn;
    @FXML
    private TextField appointmentIdField;
    @FXML
    private TextField appointmentTitleField;
    @FXML
    private TextField appointmentDescriptionField;
    @FXML
    private TextField appointmentLocationField;
    @FXML
    private ComboBox<Contact> appointmentContactComboBox;
    @FXML
    private TextField appointmentTypeField;
    @FXML
    private DatePicker appointmentStartDatePicker;
    @FXML
    private Spinner<Integer> appointmentStartHoursSpinner;
    @FXML
    private Spinner<Integer> appointmentStartMinutesSpinner;
    @FXML
    private DatePicker appointmentEndDatePicker;
    @FXML
    private Spinner<Integer> appointmentEndHoursSpinner;
    @FXML
    private Spinner<Integer> appointmentEndMinutesSpinner;
    @FXML
    private ComboBox<Customer> appointmentCustomerIdComboBox;
    @FXML
    private ComboBox<User> appointmentUserIdComboBox;
    @FXML
    private Button saveAppointmentButton;
    @FXML
    private Button deleteAppointmentButton;
    @FXML
    private Button newAppointmentButton;
    //Reports
    @FXML
    private Button reportsMenuButton;
    @FXML
    private BorderPane reportsPane;
    @FXML
    private Button backToMenuFromReportsButton;
    @FXML
    private Button generateAppointmentsByTypeMonthButton;
    @FXML
    private TextArea appointmentsByTypeMonthTextArea;
    @FXML
    private Button generateContactsAppointmentsScheduleButton;
    @FXML
    private TextArea contactsAppointmentsScheduleTextArea;
    @FXML
    private Button generateTotalCustomerAppointmentsButton;
    @FXML
    private TextArea totalCustomerAppointmentsTextArea;

    /**
     * A lambda expression:
     * <pre>
     * {@code
     * reportsMenuButton.setOnAction(event -> showReportsPane());
     * }
     * </pre>
     * <p>
     * is used in this method because it provides concise syntax. It expresses
     * functionality in a more compact and readable form. It reduced the
     * verbosity of the code.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showMainMenu();

        EventHandler<ActionEvent> showMainMenuAction = event -> showMainMenu();

        // Reports
        reportsMenuButton.setOnAction(event -> showReportsPane());
        backToMenuFromReportsButton.setOnAction(showMainMenuAction);

        generateAppointmentsByTypeMonthButton.setOnAction(event -> {
            appointmentsByTypeMonthTextArea.clear();

            // Query to get the total number of appointments by type and month
            String sql = "SELECT MONTH(start) AS month, type, COUNT(*) AS total "
                    + "FROM appointments "
                    + "GROUP BY month, type";

            try (Connection conn = JdbcConnection.openConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                // Initialize a hashmap to store the results
                Map<String, Map<String, Integer>> reportData = new HashMap<>();

                // Loop through the result set and populate the hashmap
                while (rs.next()) {
                    String month = Month.of(rs.getInt("month")).toString();
                    String type = rs.getString("type");
                    int total = rs.getInt("total");

                    if (!reportData.containsKey(month)) {
                        reportData.put(month, new HashMap<>());
                    }
                    reportData.get(month).put(type, total);
                }

                // Display the report in the user interface
                for (String month : reportData.keySet()) {
                    appointmentsByTypeMonthTextArea.appendText(month + " Report:" + System.lineSeparator());
                    for (String type : reportData.get(month).keySet()) {
                        int total = reportData.get(month).get(type);
                        appointmentsByTypeMonthTextArea.appendText("- " + type + ": " + total + System.lineSeparator());
                    }
                    appointmentsByTypeMonthTextArea.appendText(System.lineSeparator());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        });

        generateContactsAppointmentsScheduleButton.setOnAction(event -> {
            contactsAppointmentsScheduleTextArea.clear();

            String sql = "SELECT a.Appointment_ID, a.Title, a.Type, a.Description, a.Start, a.End, a.Customer_ID, c.Contact_Name "
                    + "FROM appointments a "
                    + "INNER JOIN contacts c ON a.Contact_ID = c.Contact_ID "
                    + "ORDER BY c.Contact_Name";
            try (Connection conn = JdbcConnection.openConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
                // Initialize a hashmap to store the schedule for each contact
                Map<String, List<String>> contactSchedule = new HashMap<>();

                // Loop through the result set and populate the hashmap
                while (rs.next()) {
                    String contactName = rs.getString("Contact_Name");
                    String scheduleEntry = "Appointment ID: " + rs.getInt("Appointment_ID") + System.lineSeparator()
                            + "Title: " + rs.getString("Title") + System.lineSeparator()
                            + "Type: " + rs.getString("Type") + System.lineSeparator()
                            + "Description: " + rs.getString("Description") + System.lineSeparator()
                            + "Start: " + rs.getTimestamp("Start") + System.lineSeparator()
                            + "End: " + rs.getTimestamp("End") + System.lineSeparator()
                            + "Customer ID: " + rs.getInt("Customer_ID") + System.lineSeparator();

                    if (!contactSchedule.containsKey(contactName)) {
                        contactSchedule.put(contactName, new ArrayList<>());
                    }
                    contactSchedule.get(contactName).add(scheduleEntry);
                }

                // Display the schedule report in the user interface
                for (String contactName : contactSchedule.keySet()) {
                    contactsAppointmentsScheduleTextArea.appendText("Schedule for Contact: " + contactName + System.lineSeparator());
                    List<String> scheduleEntries = contactSchedule.get(contactName);
                    for (String scheduleEntry : scheduleEntries) {
                        contactsAppointmentsScheduleTextArea.appendText(scheduleEntry + System.lineSeparator());
                    }
                }

            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        });

        generateTotalCustomerAppointmentsButton.setOnAction(event -> {
            totalCustomerAppointmentsTextArea.clear();

            String sql = "SELECT c.Customer_ID, c.Customer_Name, COUNT(*) AS TotalAppointments "
                    + "FROM customers c "
                    + "INNER JOIN appointments a ON c.Customer_ID = a.Customer_ID "
                    + "GROUP BY c.Customer_ID, c.Customer_Name";
            try (Connection conn = JdbcConnection.openConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int customerId = rs.getInt("Customer_ID");
                    String customerName = rs.getString("Customer_Name");
                    int totalAppointments = rs.getInt("TotalAppointments");
                    totalCustomerAppointmentsTextArea.appendText("Customer ID: " + customerId + System.lineSeparator());
                    totalCustomerAppointmentsTextArea.appendText("Customer Name: " + customerName + System.lineSeparator());
                    totalCustomerAppointmentsTextArea.appendText("Total Appointments: " + totalAppointments + System.lineSeparator());
                    totalCustomerAppointmentsTextArea.appendText(System.lineSeparator());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        });

        // Appointments
        appointmentsMenuButton.setOnAction(event -> showAppointmentsPane());
        backToMenuFromAppointmentsButton.setOnAction(showMainMenuAction);

        clearAppointmentsFilterRadio.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                loadAppointments();
            }
        });

        filterAppointmentsByMonthRadio.selectedProperty().addListener((observable, oldValue, newValue) -> {
            appointmentMonthsComboBox.setDisable(!newValue);
        });

        appointmentMonthsComboBox.disableProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                SingleSelectionModel<Month> monthsSelectionModel = appointmentMonthsComboBox.getSelectionModel();
                Month selectedMonth = monthsSelectionModel.getSelectedItem();
                monthsSelectionModel.clearSelection();
                Platform.runLater(() -> monthsSelectionModel.select(selectedMonth));
            }
        });

        appointmentMonthsComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!appointmentMonthsComboBox.isDisable() && newValue != null) {
                List<Appointment> filteredAppointments = new ArrayList<>();
                loadAppointments();
                for (Appointment appointment : appointments) {
                    if (appointment.getStart() != null && appointment.getStart().getMonth().equals(newValue)) {
                        filteredAppointments.add(appointment);
                    }
                }

                appointments.clear();
                appointments.setAll(filteredAppointments);
            }
        });

        filterAppointmentsByWeekRadio.selectedProperty().addListener((observable, oldValue, newValue) -> {
            appointmentWeeksComboBox.setDisable(!newValue);
        });

        appointmentWeeksComboBox.disableProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                SingleSelectionModel<String> weeksSelectionModel = appointmentWeeksComboBox.getSelectionModel();
                String selectedWeek = weeksSelectionModel.getSelectedItem();
                weeksSelectionModel.clearSelection();
                Platform.runLater(() -> weeksSelectionModel.select(selectedWeek));
            }
        });

        appointmentWeeksComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!appointmentWeeksComboBox.isDisable() && newValue != null) {
                List<Appointment> filteredAppointments = new ArrayList<>();
                loadAppointments();
                for (Appointment appointment : appointments) {
                    if (appointment.getStart() != null) {
                        LocalDate appointmentDate = appointment.getStart().toLocalDate();
                        String week = "Week " + appointmentDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
                        if (week.equals(newValue)) {
                            filteredAppointments.add(appointment);
                        }
                    }
                }

                appointments.clear();
                appointments.setAll(filteredAppointments);
            }
        });

        loadAppointmentMonths();
        appointmentMonthsComboBox.setItems(appointmentMonths);
        Platform.runLater(() -> appointmentMonthsComboBox.getSelectionModel().selectFirst());

        loadAppointmentWeeks();
        appointmentWeeksComboBox.setItems(appointmentWeeks);
        Platform.runLater(() -> appointmentWeeksComboBox.getSelectionModel().selectFirst());

        newAppointmentButton.setOnAction(event -> {
            appointmentsTable.getSelectionModel().clearSelection();
            clearAppointmentFields();
        });
        deleteAppointmentButton.setOnAction(event -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Appointment");
            alert.setHeaderText(null);
            Appointment selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
            alert.setContentText(String.format("Delete %s?", selectedAppointment.getTitle()));
            Optional<ButtonType> selectedButton = alert.showAndWait();

            if (selectedButton.isPresent() && selectedButton.get() == ButtonType.OK) {
                deleteAppointment(selectedAppointment.getAppointmentId());
            }
        });
        saveAppointmentButton.setOnAction(event -> {
            Appointment selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();

            if (selectedAppointment != null) {
                LocalDate startDate = appointmentStartDatePicker.getValue();
                int startHours = appointmentStartHoursSpinner.getValue();
                int startMinutes = appointmentStartMinutesSpinner.getValue();
                LocalDateTime startDateTime = startDate.atTime(startHours, startMinutes);

                LocalDate endDate = appointmentEndDatePicker.getValue();
                int endHours = appointmentEndHoursSpinner.getValue();
                int endMinutes = appointmentEndMinutesSpinner.getValue();
                LocalDateTime endDateTime = endDate.atTime(endHours, endMinutes);

                // Get the system's default timezone
                ZoneId localZoneId = ZoneId.systemDefault();
                // Get the EST timezone
                ZoneId estZoneId = ZoneId.of("America/New_York");

                // Convert the local date and time to EST timezone
                ZonedDateTime estStartDateTime = startDateTime.atZone(localZoneId).withZoneSameInstant(estZoneId);
                ZonedDateTime estEndDateTime = endDateTime.atZone(localZoneId).withZoneSameInstant(estZoneId);

                System.out.printf("startDateTime hr: %d; estStartDateTime hr: %d%n", startDateTime.getHour(), estStartDateTime.getHour());

                // Check if the appointment start and end times are within 8am and 10pm EST
                if (estStartDateTime.getHour() < 8 || estEndDateTime.getHour() >= 22) {
                    // Show an error message and do not save the appointment
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Invalid Appointment Time");
                    alert.setContentText("Appointments can only be scheduled between 8:00 AM and 10:00 PM Eastern Standard Time.");
                    alert.showAndWait();
                    return;
                }

                if (appointmentOverlaps(startDateTime, endDateTime, selectedAppointment.getAppointmentId())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Invalid Appointment Time");
                    alert.setContentText("Appointment overlaps with other appointment times.");
                    alert.showAndWait();
                    return;
                }

                selectedAppointment.setTitle(appointmentTitleField.getText());
                selectedAppointment.setDescription(appointmentDescriptionField.getText());
                selectedAppointment.setLocation(appointmentLocationField.getText());
                selectedAppointment.setContactId(appointmentContactComboBox.getSelectionModel().getSelectedItem().getContactID());
                selectedAppointment.setType(appointmentTypeField.getText());
                selectedAppointment.setStart(startDateTime);
                selectedAppointment.setEnd(endDateTime);
                selectedAppointment.setCustomerId(appointmentCustomerIdComboBox.getSelectionModel().getSelectedItem().getCustomerId());
                selectedAppointment.setUserId(appointmentUserIdComboBox.getSelectionModel().getSelectedItem().getUserId());

                updateAppointment(selectedAppointment);
            } else {
                insertNewAppointment();
            }
        });

        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentContactColumn.setCellValueFactory(cellData -> {
            String contactName = "";
            String sql = "SELECT Contact_Name FROM contacts WHERE Contact_ID = ?";
            try (Connection conn = JdbcConnection.openConnection();
                    PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, cellData.getValue().getContactId());
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        contactName = rs.getString("Contact_Name");
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            return new SimpleStringProperty(contactName);
        });
        appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStartDateColumn.setCellValueFactory(cellData -> {
            ZonedDateTime zonedStart = cellData.getValue().getStart().atZone(ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
            return new SimpleStringProperty(zonedStart.format(formatter));
        });
        appointmentEndDateColumn.setCellValueFactory(cellData -> {
            ZonedDateTime zonedEnd = cellData.getValue().getEnd().atZone(ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
            return new SimpleStringProperty(zonedEnd.format(formatter));
        });
        appointmentCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        StringConverter<LocalDate> localDateStringConverter = new StringConverter<LocalDate>() {
            private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null) {
                    return "";
                }
                return dateFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString == null || dateString.isBlank()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateFormatter);
            }
        };

        appointmentStartDatePicker.setConverter(localDateStringConverter);
        appointmentEndDatePicker.setConverter(localDateStringConverter);

        appointmentStartHoursSpinner.setValueFactory(new IntegerSpinnerValueFactory(0, 23, 0));
        appointmentStartMinutesSpinner.setValueFactory(new IntegerSpinnerValueFactory(0, 59, 0));
        appointmentEndHoursSpinner.setValueFactory(new IntegerSpinnerValueFactory(0, 23, 0));
        appointmentEndMinutesSpinner.setValueFactory(new IntegerSpinnerValueFactory(0, 59, 0));

        appointmentsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                appointmentIdField.setText(newValue.getAppointmentId() + "");
                appointmentTitleField.setText(newValue.getTitle());
                appointmentDescriptionField.setText(newValue.getDescription());
                appointmentLocationField.setText(newValue.getLocation());

                Contact selectContact = null;
                for (Contact appointmentContact : appointmentContacts) {
                    if (appointmentContact.getContactID() == newValue.getContactId()) {
                        selectContact = appointmentContact;
                        break;
                    }
                }
                appointmentContactComboBox.getSelectionModel().select(selectContact);
                appointmentTypeField.setText(newValue.getType());
                appointmentStartDatePicker.setValue(newValue.getStart().toLocalDate());
                appointmentStartHoursSpinner.getValueFactory().setValue(newValue.getStart().getHour());
                appointmentStartMinutesSpinner.getValueFactory().setValue(newValue.getStart().getMinute());
                appointmentEndDatePicker.setValue(newValue.getEnd().toLocalDate());
                appointmentEndHoursSpinner.getValueFactory().setValue(newValue.getEnd().getHour());
                appointmentEndMinutesSpinner.getValueFactory().setValue(newValue.getEnd().getMinute());

                Customer selectCustomer = null;
                for (Customer appointmentCustomer : appointmentCustomers) {
                    if (appointmentCustomer.getCustomerId() == newValue.getCustomerId()) {
                        selectCustomer = appointmentCustomer;
                        break;
                    }
                }
                appointmentCustomerIdComboBox.getSelectionModel().select(selectCustomer);

                User selectUser = null;
                for (User appointmentUser : appointmentUsers) {
                    if (appointmentUser.getUserId() == newValue.getUserId()) {
                        selectUser = appointmentUser;
                        break;
                    }
                }
                appointmentUserIdComboBox.getSelectionModel().select(selectUser);
            } else {
                clearAppointmentFields();
            }
        });

        loadAppointments();
        loadAppointmentContacts();
        loadAppointmentCustomers();
        loadAppointmentUsers();

        appointmentsTable.setItems(appointments);
        appointmentContactComboBox.setItems(appointmentContacts);
        appointmentCustomerIdComboBox.setItems(appointmentCustomers);
        appointmentUserIdComboBox.setItems(appointmentUsers);

        Platform.runLater(() -> appointmentsTable.getSelectionModel().selectFirst());

        // Customer Records
        customersRecordsMenuButton.setOnAction(event -> showCustomersRecordsPane());
        backToMenuFromCustomersRecordsButton.setOnAction(showMainMenuAction);
        newCustomerButton.setOnAction(event -> {
            customersTable.getSelectionModel().clearSelection();
            clearCustomerFields();
        });
        saveCustomerButton.setOnAction(event -> {
            Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                selectedCustomer.setCustomerName(customerNameField.getText());
                selectedCustomer.setAddress(customerAddressField.getText());
                selectedCustomer.setPostalCode(customerPostalCodeField.getText());
                selectedCustomer.setPhone(customerPhoneField.getText());
                selectedCustomer.setDivisionId(customerDivisionComboBox.getSelectionModel().getSelectedItem().getDivisionId());

                updateCustomer(selectedCustomer);
            } else {
                insertNewCustomer();
            }
        });
        deleteCustomerButton.setOnAction(event -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Customer");
            alert.setHeaderText(null);
            Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
            alert.setContentText(String.format("Delete %s?", selectedCustomer.getCustomerName()));
            Optional<ButtonType> selectedButton = alert.showAndWait();

            if (selectedButton.isPresent() && selectedButton.get() == ButtonType.OK) {
                deleteCustomer(selectedCustomer.getCustomerId());
            }
        });

        customersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                customerIdField.setText(newValue.getCustomerId() + "");
                customerNameField.setText(newValue.getCustomerName());
                customerAddressField.setText(newValue.getAddress());
                customerPostalCodeField.setText(newValue.getPostalCode());
                customerPhoneField.setText(newValue.getPhone());

                String sql = "SELECT c.Country_ID, c.Country FROM countries c "
                        + "JOIN first_level_divisions f ON c.Country_ID = f.COUNTRY_ID "
                        + "WHERE f.Division_ID = ?";
                try (Connection conn = JdbcConnection.openConnection();
                        PreparedStatement ps = conn.prepareStatement(sql);) {

                    ps.setInt(1, newValue.getDivisionId());

                    int countryId = 0;
                    String countryName = "";

                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            countryId = rs.getInt("Country_ID");
                            countryName = rs.getString("Country");
                        }
                    }

                    System.out.printf("countryId: %s; countryName: %s%n", countryId, countryName);

                    if (countryId != 0) {
                        Country customerCountry = null;
                        for (Country country : customerCountryComboBox.getItems()) {
                            if (country.getCountryId() == countryId) {
                                customerCountry = country;
                                break;
                            }
                        }
                        Country selectCountry = customerCountry;
                        if (selectCountry != null) {
                            Platform.runLater(() -> customerCountryComboBox.getSelectionModel().select(selectCountry));
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        });

        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        customerDivisionColumn.setCellValueFactory((cellData) -> {
            String divisionName = "";
            String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = ?";
            try (Connection conn = JdbcConnection.openConnection();
                    PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, cellData.getValue().getDivisionId());
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        divisionName = rs.getString("Division");
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            return new SimpleStringProperty(divisionName);
        });

        customersTable.setItems(customers);
        customerCountryComboBox.setItems(customerCountries);
        customerDivisionComboBox.setItems(customerDivisions);

        customerCountryComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            customerDivisions.clear();
            if (newValue != null) {
                String query = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
                try (Connection conn = JdbcConnection.openConnection();
                        PreparedStatement ps = conn.prepareStatement(query);) {

                    ps.setInt(1, newValue.getCountryId());

                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            FirstLevelDivision division = new FirstLevelDivision();
                            division.setDivisionId(rs.getInt("Division_ID"));
                            division.setDivision(rs.getString("Division"));
                            division.setCountryId(newValue.getCountryId());
                            division.setCreateDate(rs.getTimestamp("Create_Date").toLocalDateTime());
                            division.setCreatedBy(rs.getString("Created_By"));
                            division.setLastUpdate(rs.getTimestamp("Last_Update").toLocalDateTime());
                            division.setLastUpdatedBy(rs.getString("Last_Updated_By"));

                            customerDivisions.add(division);
                        }
                    }

                    Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();

                    if (selectedCustomer == null) {
                        Platform.runLater(() -> customerDivisionComboBox.getSelectionModel().selectFirst());
                    } else {
                        FirstLevelDivision customerDivision = null;
                        for (FirstLevelDivision division : customerDivisions) {
                            if (division.getDivisionId() == selectedCustomer.getDivisionId()) {
                                customerDivision = division;
                                break;
                            }
                        }
                        FirstLevelDivision selectDivision = customerDivision;
                        if (selectDivision != null) {
                            Platform.runLater(() -> customerDivisionComboBox.getSelectionModel().select(selectDivision));
                        } else {
                            Platform.runLater(() -> customerDivisionComboBox.getSelectionModel().selectFirst());
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        });

        loadCustomers(customers);
        loadCustomerCountries();

        Platform.runLater(() -> customersTable.getSelectionModel().selectFirst());
    }

    void setUser(User user) {
        this.user = user;
    }

    private void loadAppointmentMonths() {
        // Get the current year and month
        YearMonth currentYearMonth = YearMonth.now();

        // Create a list of the 12 months of the year
        for (int i = 1; i <= 12; i++) {
            //String month = currentYearMonth.withMonth(i).getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
            appointmentMonths.add(currentYearMonth.withMonth(i).getMonth());
        }
    }

    private void loadAppointmentWeeks() {
        // Create a list of the weeks in the current year
        LocalDate firstDayOfYear = LocalDate.of(YearMonth.now().getYear(), 1, 1);
        for (int i = 1; i < 53; i++) {
            LocalDate firstDayOfWeek = firstDayOfYear.plusWeeks(i);
            String week = "Week " + firstDayOfWeek.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
            appointmentWeeks.add(week);
        }
    }

    private void showMainMenu() {
        menuVBox.setVisible(true);
        customersRecordsPane.setVisible(false);
        appointmentsPane.setVisible(false);
        reportsPane.setVisible(false);
    }

    private void showReportsPane() {
        menuVBox.setVisible(false);
        customersRecordsPane.setVisible(false);
        appointmentsPane.setVisible(false);
        reportsPane.setVisible(true);
    }

    private void showAppointmentsPane() {
        menuVBox.setVisible(false);
        customersRecordsPane.setVisible(false);
        appointmentsPane.setVisible(true);
        reportsPane.setVisible(false);
    }

    private boolean appointmentOverlaps(LocalDateTime start, LocalDateTime end,
            int appointmentId) {
        boolean overlaps = false;
        String sql = "SELECT * FROM appointments "
                + "WHERE ((? BETWEEN start AND end) OR (? BETWEEN start AND end) OR (start BETWEEN ? AND ?) OR (end BETWEEN ? AND ?)) "
                + "AND (Appointment_ID != ?)";
        try (Connection conn = JdbcConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            // Get the system's default timezone
            ZoneId localZoneId = ZoneId.systemDefault();
            ZoneId utcZoneId = ZoneId.of("UTC");
            Timestamp utcStart = Timestamp.valueOf(start.atZone(localZoneId).withZoneSameInstant(utcZoneId).toLocalDateTime());
            Timestamp utcEnd = Timestamp.valueOf(end.atZone(localZoneId).withZoneSameInstant(utcZoneId).toLocalDateTime());

            ps.setTimestamp(1, utcStart);
            ps.setTimestamp(2, utcEnd);
            ps.setTimestamp(3, utcStart);
            ps.setTimestamp(4, utcEnd);
            ps.setTimestamp(5, utcStart);
            ps.setTimestamp(6, utcEnd);
            ps.setInt(7, appointmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                overlaps = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return overlaps;
    }

    private void clearAppointmentFields() {
        appointmentIdField.setText("<autogenerated>");
        appointmentTitleField.clear();
        appointmentDescriptionField.clear();
        appointmentLocationField.clear();
        Platform.runLater(() -> appointmentContactComboBox.getSelectionModel().selectFirst());
        appointmentTypeField.clear();

        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();

        appointmentStartDatePicker.setValue(today);
        appointmentStartHoursSpinner.getValueFactory().setValue(now.getHour());
        appointmentStartMinutesSpinner.getValueFactory().setValue(now.getMinute());
        appointmentEndDatePicker.setValue(today);
        appointmentEndHoursSpinner.getValueFactory().setValue(now.plusHours(1).getHour());
        appointmentEndMinutesSpinner.getValueFactory().setValue(now.plusHours(1).getMinute());

        Platform.runLater(() -> appointmentCustomerIdComboBox.getSelectionModel().selectFirst());
        Platform.runLater(() -> appointmentUserIdComboBox.getSelectionModel().selectFirst());
    }

    private void insertNewAppointment() {
        LocalDate startDate = appointmentStartDatePicker.getValue();
        int startHours = appointmentStartHoursSpinner.getValue();
        int startMinutes = appointmentStartMinutesSpinner.getValue();
        LocalDateTime startDateTime = startDate.atTime(startHours, startMinutes);

        LocalDate endDate = appointmentEndDatePicker.getValue();
        int endHours = appointmentEndHoursSpinner.getValue();
        int endMinutes = appointmentEndMinutesSpinner.getValue();
        LocalDateTime endDateTime = endDate.atTime(endHours, endMinutes);

        // Get the system's default timezone
        ZoneId localZoneId = ZoneId.systemDefault();
        // Get the EST timezone
        ZoneId estZoneId = ZoneId.of("America/New_York");

        // Convert the local date and time to EST timezone
        ZonedDateTime estStartDateTime = startDateTime.atZone(localZoneId).withZoneSameInstant(estZoneId);
        ZonedDateTime estEndDateTime = endDateTime.atZone(localZoneId).withZoneSameInstant(estZoneId);

        System.out.printf("startDateTime hr: %d; estStartDateTime hr: %d%n", startDateTime.getHour(), estStartDateTime.getHour());

        // Check if the appointment start and end times are within 8am and 10pm EST
        if (estStartDateTime.getHour() < 8 || estEndDateTime.getHour() >= 22) {
            // Show an error message and do not save the appointment
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid Appointment Time");
            alert.setContentText("Appointments can only be scheduled between 8:00 AM and 10:00 PM Eastern Standard Time.");
            alert.showAndWait();
            return;
        }

        if (appointmentOverlaps(startDateTime, endDateTime, -1)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid Appointment Time");
            alert.setContentText("Appointment overlaps with other appointment times.");
            alert.showAndWait();
            return;
        }

        Appointment appointment = new Appointment();
        appointment.setTitle(appointmentTitleField.getText());
        appointment.setDescription(appointmentDescriptionField.getText());
        appointment.setLocation(appointmentLocationField.getText());
        appointment.setContactId(appointmentContactComboBox.getSelectionModel().getSelectedItem().getContactID());
        appointment.setType(appointmentTypeField.getText());
        appointment.setStart(startDateTime);
        appointment.setEnd(endDateTime);
        appointment.setCustomerId(appointmentCustomerIdComboBox.getSelectionModel().getSelectedItem().getCustomerId());
        appointment.setUserId(appointmentUserIdComboBox.getSelectionModel().getSelectedItem().getUserId());

        String sql = "INSERT INTO appointments "
                + "(Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) "
                + "VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
        try (Connection conn = JdbcConnection.openConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, appointment.getTitle());
            preparedStatement.setString(2, appointment.getDescription());
            preparedStatement.setString(3, appointment.getLocation());
            preparedStatement.setString(4, appointment.getType());

            // Convert the local date and time to UTC timezone
            ZonedDateTime utcStartDateTime = appointment.getStart().atZone(localZoneId).withZoneSameInstant(ZoneOffset.UTC);
            ZonedDateTime utcEndDateTime = appointment.getEnd().atZone(localZoneId).withZoneSameInstant(ZoneOffset.UTC);

            preparedStatement.setTimestamp(5, Timestamp.valueOf(utcStartDateTime.toLocalDateTime()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(utcEndDateTime.toLocalDateTime()));
            preparedStatement.setString(7, user.getUserName());
            preparedStatement.setString(8, user.getUserName());
            preparedStatement.setInt(9, appointment.getCustomerId());
            preparedStatement.setInt(10, appointment.getUserId());
            preparedStatement.setInt(11, appointment.getContactId());
            preparedStatement.executeUpdate();

            // Get the generated ID
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                appointment.setAppointmentId(generatedId);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        if (appointment.getAppointmentId() != 0) {
            appointments.add(appointment);
            appointmentsTable.getSelectionModel().clearSelection();
            clearAppointmentFields();
        }
    }

    private void deleteAppointment(int appointmentId) {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";

        try (Connection conn = JdbcConnection.openConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointmentId);
            stmt.executeUpdate();

            Appointment appointmentToRemove = null;
            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentId() == appointmentId) {
                    appointmentToRemove = appointment;
                    break;
                }
            }

            if (appointmentToRemove != null) {
                appointments.remove(appointmentToRemove);
                Platform.runLater(() -> appointmentsTable.getSelectionModel().selectFirst());
                if (appointments.isEmpty()) {
                    clearAppointmentFields();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateAppointment(Appointment appointment) {
        String sql = "UPDATE appointments SET "
                + "Title=?, "
                + "Description=?, "
                + "Location=?, "
                + "Type=?, "
                + "Start=?, "
                + "End=?, "
                + "Last_Update=?, "
                + "Last_Updated_By=?, "
                + "Customer_ID=?, "
                + "User_ID=?, "
                + "Contact_ID=? "
                + "WHERE Appointment_ID=?";
        try (Connection conn = JdbcConnection.openConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, appointment.getTitle());
            stmt.setString(2, appointment.getDescription());
            stmt.setString(3, appointment.getLocation());
            stmt.setString(4, appointment.getType());

            // Get the system's default timezone
            ZoneId localZoneId = ZoneId.systemDefault();

            // Convert the local date and time to UTC timezone
            ZonedDateTime utcStartDateTime = appointment.getStart().atZone(localZoneId).withZoneSameInstant(ZoneOffset.UTC);
            ZonedDateTime utcEndDateTime = appointment.getEnd().atZone(localZoneId).withZoneSameInstant(ZoneOffset.UTC);

            System.out.printf("local hr: %d; utc hr: %d%n", appointment.getStart().getHour(), utcStartDateTime.getHour());

            stmt.setTimestamp(5, Timestamp.valueOf(utcStartDateTime.toLocalDateTime()));
            stmt.setTimestamp(6, Timestamp.valueOf(utcEndDateTime.toLocalDateTime()));

            stmt.setTimestamp(7, Timestamp.from(Instant.now()));
            stmt.setString(8, user.getUserName());
            stmt.setInt(9, appointment.getCustomerId());
            stmt.setInt(10, appointment.getUserId());
            stmt.setInt(11, appointment.getContactId());
            stmt.setInt(12, appointment.getAppointmentId());

            stmt.executeUpdate();

            int appointmentIndex = -1;

            for (Appointment appt : appointments) {
                if (appt.getAppointmentId() == appointment.getAppointmentId()) {
                    appointmentIndex = appointments.indexOf(appt);
                    break;
                }
            }

            if (appointmentIndex != -1) {
                appointments.set(appointmentIndex, appointment);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void loadAppointments() {
        appointments.clear();

        String sql = "SELECT * FROM appointments";

        try (Connection conn = JdbcConnection.openConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // Get the system's default timezone
            ZoneId localZoneId = ZoneId.systemDefault();
            ZoneId utcZoneId = ZoneId.of("UTC");

            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(rs.getInt("Appointment_ID"));
                appointment.setTitle(rs.getString("Title"));
                appointment.setDescription(rs.getString("Description"));
                appointment.setLocation(rs.getString("Location"));
                appointment.setType(rs.getString("Type"));
                appointment.setStart(rs.getTimestamp("Start").toLocalDateTime().atZone(utcZoneId).withZoneSameInstant(localZoneId).toLocalDateTime());
                appointment.setEnd(rs.getTimestamp("End").toLocalDateTime().atZone(utcZoneId).withZoneSameInstant(localZoneId).toLocalDateTime());
                appointment.setCreateDate(rs.getTimestamp("Create_Date").toLocalDateTime());
                appointment.setCreatedBy(rs.getString("Created_By"));
                appointment.setLastUpdate(rs.getTimestamp("Last_Update").toLocalDateTime());
                appointment.setLastUpdatedBy(rs.getString("Last_Updated_By"));
                appointment.setCustomerId(rs.getInt("Customer_ID"));
                appointment.setUserId(rs.getInt("User_ID"));
                appointment.setContactId(rs.getInt("Contact_ID"));

                appointments.add(appointment);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Loads the list of contacts from the database.
     * <p>
     * The contacts are loaded into an ObservableList of Contact objects.
     */
    private void loadAppointmentContacts() {
        appointmentContacts.clear();

        String sql = "SELECT * FROM contacts";
        try (Connection conn = JdbcConnection.openConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                Contact contact = new Contact();
                contact.setContactID(rs.getInt("Contact_ID"));
                contact.setContactName(rs.getString("Contact_Name"));
                contact.setEmail(rs.getString("Email"));
                appointmentContacts.add(contact);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Loads the list of customers from the database.
     * <p>
     * The customers are loaded into an ObservableList of Customer objects.
     */
    private void loadAppointmentCustomers() {
        loadCustomers(appointmentCustomers);
    }

    /**
     * Loads the list of users from the database.
     * <p>
     * The users are loaded into an ObservableList of User objects.
     */
    private void loadAppointmentUsers() {
        appointmentUsers.clear();

        try (Connection conn = JdbcConnection.openConnection();
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM users");
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("User_ID"));
                u.setUserName(rs.getString("User_Name"));
                u.setPassword(rs.getString("Password"));
                u.setCreateDate(rs.getTimestamp("Create_Date").toLocalDateTime());
                u.setCreatedBy(rs.getString("Created_By"));
                u.setLastUpdate(rs.getTimestamp("Last_Update").toLocalDateTime());
                u.setLastUpdatedBy(rs.getString("Last_Updated_By"));
                appointmentUsers.add(u);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Hides the menuVBox, appointmentsPane, and reportsPane panes, and shows
     * the customersRecordsPane pane.
     * <p>
     * This method is used to switch to the customer records view.
     */
    private void showCustomersRecordsPane() {
        menuVBox.setVisible(false);
        customersRecordsPane.setVisible(true);
        appointmentsPane.setVisible(false);
        reportsPane.setVisible(false);
    }

    /**
     * This method clears the customer fields in the UI.
     * <p>
     * The fields are cleared by setting their text to an empty string or by
     * selecting the first item in the combo box.
     * <p>
     * A lambda expression has been used:
     * <pre>
     * {@code
     * Platform.runLater(() -> customerCountryComboBox.getSelectionModel().selectFirst());
     * }
     * </pre>
     * <p>
     * This approach has helped make the code more expressive by reducing the
     * syntactic noise since the instead of declaring and using an anonymous
     * inner class, a one-liner lambda expression has served just as fine.
     */
    private void clearCustomerFields() {
        customerIdField.setText("<autogenerated>");
        customerNameField.clear();
        customerAddressField.clear();
        customerPostalCodeField.clear();
        customerPhoneField.clear();
        Platform.runLater(() -> customerCountryComboBox.getSelectionModel().selectFirst());
    }

    /**
     * This method deletes the customer from the database.
     * <p>
     * It uses the JDBC API to connect to a database and delete the customer
     * with the specified ID. The customer is also deleted from the appointments
     * and appointmentCustomers lists.
     *
     * @param customerId The ID of the customer to delete.
     */
    private void deleteCustomer(int customerId) {
        // delete related appointments first
        try (Connection conn = JdbcConnection.openConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM appointments WHERE Customer_ID = ?")) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        // delete customer
        try (Connection conn = JdbcConnection.openConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM customers WHERE Customer_ID = ?")) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();

            Customer customerToRemove = null;

            for (Customer customer : customers) {
                if (customer.getCustomerId() == customerId) {
                    customerToRemove = customer;
                    break;
                }
            }

            if (customerToRemove != null) {
                customers.remove(customerToRemove);

                System.out.printf("Removing %s from appointment customers...%n", customerToRemove);

                Customer apptCustomerToRemove = null;
                for (Customer appointmentCustomer : appointmentCustomers) {
                    if (appointmentCustomer.getCustomerId() == customerId) {
                        apptCustomerToRemove = appointmentCustomer;
                        break;
                    }
                }

                if (apptCustomerToRemove != null) {
                    appointmentCustomers.remove(apptCustomerToRemove);

                    System.out.printf("After removing; remaining customers: %s%n", appointmentCustomers);
                }

                List<Appointment> appointmentsToRemove = new ArrayList<>();
                for (Appointment appointment : appointments) {
                    if (appointment.getCustomerId() == customerId) {
                        appointmentsToRemove.add(appointment);
                    }
                }

                if (!appointmentsToRemove.isEmpty()) {
                    for (Appointment appointment : appointmentsToRemove) {
                        appointments.remove(appointment);
                    }
                    Platform.runLater(() -> appointmentsTable.getSelectionModel().selectFirst());
                    if (appointments.isEmpty()) {
                        clearAppointmentFields();
                    }
                }
                Platform.runLater(() -> customersTable.getSelectionModel().selectFirst());
                if (customers.isEmpty()) {
                    clearCustomerFields();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * This method uses the JDBC API to connect to a database and update the
     * customer with the specified name, address, postal code, phone number,
     * last update, last updated by, and division ID.
     *
     * @param customer The customer to update.
     */
    private void updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Last_Update=?, Last_Updated_By=?, Division_ID=? WHERE Customer_ID=?";

        try (Connection conn = JdbcConnection.openConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getCustomerName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPostalCode());
            stmt.setString(4, customer.getPhone());
            stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            stmt.setString(6, user.getUserName());
            stmt.setInt(7, customer.getDivisionId());
            stmt.setInt(8, customer.getCustomerId());

            stmt.executeUpdate();

            int customerIndex = -1;

            for (Customer cust : customers) {
                if (cust.getCustomerId() == customer.getCustomerId()) {
                    customerIndex = customers.indexOf(cust);
                    break;
                }
            }

            if (customerIndex != -1) {
                customers.set(customerIndex, customer);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * This method uses the JDBC API to connect to a database and insert a new
     * customer.
     * <p>
     * The customer is inserted into the database with the specified name,
     * address, postal code, phone number, create date, created by, last update,
     * last updated by, and division ID.
     */
    private void insertNewCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName(customerNameField.getText());
        customer.setAddress(customerAddressField.getText());
        customer.setPostalCode(customerPostalCodeField.getText());
        customer.setPhone(customerPhoneField.getText());
        customer.setCreateDate(LocalDateTime.now());
        customer.setCreatedBy(user.getUserName());
        customer.setLastUpdate(LocalDateTime.now());
        customer.setLastUpdatedBy(user.getUserName());
        customer.setDivisionId(customerDivisionComboBox.getSelectionModel().getSelectedItem().getDivisionId());

        try (Connection conn = JdbcConnection.openConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );) {
            stmt.setString(1, customer.getCustomerName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPostalCode());
            stmt.setString(4, customer.getPhone());
            stmt.setTimestamp(5, Timestamp.valueOf(customer.getCreateDate()));
            stmt.setString(6, customer.getCreatedBy());
            stmt.setTimestamp(7, Timestamp.valueOf(customer.getLastUpdate()));
            stmt.setString(8, customer.getLastUpdatedBy());
            stmt.setInt(9, customer.getDivisionId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        customer.setCustomerId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        if (customer.getCustomerId() != 0) {
            customers.add(customer);

            System.out.printf("After saving %s, now clearing selected customer on table%n", customer.getCustomerName());
            customersTable.getSelectionModel().clearSelection();
            clearCustomerFields();
        }
    }

    /**
     * This method loads the list of customers from the database.
     *
     * @param customersList The list to load the customers into.
     */
    private void loadCustomers(ObservableList<Customer> customersList) {
        customersList.clear();

        try (Connection conn = JdbcConnection.openConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("Customer_ID"));
                customer.setCustomerName(rs.getString("Customer_Name"));
                customer.setAddress(rs.getString("Address"));
                customer.setPostalCode(rs.getString("Postal_Code"));
                customer.setPhone(rs.getString("Phone"));
                customer.setCreateDate(rs.getTimestamp("Create_Date").toLocalDateTime());
                customer.setCreatedBy(rs.getString("Created_By"));
                customer.setLastUpdate(rs.getTimestamp("Last_Update").toLocalDateTime());
                customer.setLastUpdatedBy(rs.getString("Last_Updated_By"));
                customer.setDivisionId(rs.getInt("Division_ID"));
                customersList.add(customer);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    /**
     * This method loads the list of countries for the customer.
     */
    private void loadCustomerCountries() {
        try (Connection conn = JdbcConnection.openConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM countries")) {

            while (rs.next()) {
                Country country = new Country();
                country.setCountryId(rs.getInt("Country_ID"));
                country.setCountry(rs.getString("Country"));
                country.setCreateDate(rs.getTimestamp("Create_Date").toLocalDateTime());
                country.setCreatedBy(rs.getString("Created_By"));
                country.setLastUpdate(rs.getTimestamp("Last_Update").toLocalDateTime());
                country.setLastUpdatedBy(rs.getString("Last_Updated_By"));
                customerCountries.add(country);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
