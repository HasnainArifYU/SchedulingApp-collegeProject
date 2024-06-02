package ha.scheduling.app.model;

import java.time.LocalDateTime;

/**
 * A class that represents a first level division.
 */
public class FirstLevelDivision {

    /**
     * The ID of the division.
     */
    private int divisionId;

    /**
     * The name of the division.
     */
    private String division;

    /**
     * The date the division was created.
     */
    private LocalDateTime createDate;

    /**
     * The user who created the division.
     */
    private String createdBy;

    /**
     * The date the division was last updated.
     */
    private LocalDateTime lastUpdate;

    /**
     * The user who last updated the division.
     */
    private String lastUpdatedBy;

    /**
     * The ID of the country the division belongs to.
     */
    private int countryId;

    /**
     * Gets the ID of the division.
     *
     * @return The ID of the division.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Sets the ID of the division.
     *
     * @param divisionId The ID of the division.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Gets the name of the division.
     *
     * @return The name of the division.
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets the name of the division.
     *
     * @param division The name of the division.
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Gets the date the division was created.
     *
     * @return The date the division was created.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets the date the division was created.
     *
     * @param createDate The date the division was created.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the user who created the division.
     *
     * @return The user who created the division.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the division.
     *
     * @param createdBy The user who created the division.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the date the division was last updated.
     *
     * @return The date the division was last updated.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the date the division was last updated.
     *
     * @param lastUpdate The date the division was last updated.
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the user who last updated the division.
     *
     * @return The user who last updated the division.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated the division.
     *
     * @param lastUpdatedBy The user who last updated the division.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return division;
    }

}
