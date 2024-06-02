package ha.scheduling.app.model;

import java.time.LocalDateTime;

/**
 * This class represents an appointment.
 */
public class Appointment {

    /**
     * The ID of the appointment.
     */
    private int appointmentId;
    /**
     * The title of the appointment.
     */
    private String title;
    /**
     * The description of the appointment.
     */
    private String description;
    /**
     * The location of the appointment.
     */
    private String location;
    /**
     * The type of the appointment.
     */
    private String type;
    /**
     * The start date and time of the appointment.
     */
    private LocalDateTime start;
    /**
     * The end date and time of the appointment.
     */
    private LocalDateTime end;
    /**
     * The date and time the appointment was created.
     */
    private LocalDateTime createDate;
    /**
     * The user who created the appointment.
     */
    private String createdBy;
    /**
     * The date and time the appointment was last updated.
     */
    private LocalDateTime lastUpdate;
    /**
     * The user who last updated the appointment.
     */
    private String lastUpdatedBy;
    /**
     * The ID of the customer the appointment is for.
     */
    private int customerId;
    /**
     * The ID of the user the appointment is for.
     */
    private int userId;
    /**
     * The ID of the contact the appointment is for.
     */
    private int contactId;

    /**
     * Gets the ID of the appointment.
     *
     * @return The ID of the appointment.
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets the ID of the appointment.
     *
     * @param appointmentId The ID of the appointment.
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Gets the title of the appointment.
     *
     * @return The title of the appointment.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the appointment.
     *
     * @param title The title of the appointment.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the appointment.
     *
     * @return The description of the appointment.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the appointment.
     *
     * @param description The description of the appointment.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the location of the appointment.
     *
     * @return The location of the appointment.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the appointment.
     *
     * @param location The location of the appointment.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the type of the appointment.
     *
     * @return The type of the appointment.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the appointment.
     *
     * @param type The type of the appointment.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the start date and time of the appointment.
     *
     * @return The start date and time of the appointment.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Sets the start date
     *
     * @param start the start date
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Gets the end date and time of the appointment.
     *
     * @return The end date and time of the appointment.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Sets the end date and time of the appointment.
     *
     * @param end The end date and time of the appointment.
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Gets the date and time the appointment was created.
     *
     * @return The date and time the appointment was created.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets the date and time the appointment was created.
     *
     * @param createDate The date and time the appointment was created.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the user who created the appointment.
     *
     * @return The user who created the appointment.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the appointment.
     *
     * @param createdBy The user who created the appointment.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the date and time the appointment was last updated.
     *
     * @return The date and time the appointment was last updated.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the date and time the appointment was last updated.
     *
     * @param lastUpdate The date and time the appointment was last updated.
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the user who last updated the appointment.
     *
     * @return The user who last updated the appointment.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated the appointment.
     *
     * @param lastUpdatedBy The user who last updated the appointment.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Gets the ID of the customer the appointment is for.
     *
     * @return The ID of the customer the appointment is for.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the ID of the customer the appointment is for.
     *
     * @param customerId The ID of the customer the appointment is for.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the ID of the user the appointment is for.
     *
     * @return The ID of the user the appointment is for.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user the appointment is for.
     *
     * @param userId The ID of the user the appointment is for.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the ID of the contact the appointment is for.
     *
     * @return The ID of the contact the appointment is for.
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the ID of the contact the appointment is for.
     *
     * @param contactId The ID of the contact the appointment is for.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

}
