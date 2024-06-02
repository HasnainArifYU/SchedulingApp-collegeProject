package ha.scheduling.app.model;

import java.time.LocalDateTime;

/**
 * A class that represents a user.
 */
public class User {

    /**
     * The ID of the user.
     */
    private int userId;

    /**
     * The username of the user.
     */
    private String userName;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The date the user was created.
     */
    private LocalDateTime createDate;

    /**
     * The user who created the user.
     */
    private String createdBy;

    /**
     * The date the user was last updated.
     */
    private LocalDateTime lastUpdate;

    /**
     * The user who last updated the user.
     */
    private String lastUpdatedBy;

    /**
     * Gets the ID of the user.
     *
     * @return The ID of the user.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user.
     *
     * @param userId The ID of the user.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of the user.
     *
     * @param userName The username of the user.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the date the user was created.
     *
     * @return The date the user was created.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets the date the user was created.
     *
     * @param createDate The date the user was created.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the user who created the user.
     *
     * @return The user who created the user.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the user.
     *
     * @param createdBy The user who created the user.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the date the user was last updated.
     *
     * @return The date the user was last updated.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the date the user was last updated.
     *
     * @param lastUpdate The date the user was last updated.
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the user who last updated the user.
     *
     * @return The user who last updated the user.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated the user.
     *
     * @param lastUpdatedBy The user who last updated the user.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public String toString() {
        return String.format("%d - %s", userId, userName);
    }

}
