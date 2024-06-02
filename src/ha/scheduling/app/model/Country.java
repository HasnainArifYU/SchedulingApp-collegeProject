package ha.scheduling.app.model;

import java.time.LocalDateTime;

/**
 * A class that represents a country.
 */
public class Country {

    /**
     * The ID of the country.
     */
    private int countryId;

    /**
     * The name of the country.
     */
    private String country;

    /**
     * The date the country was created.
     */
    private LocalDateTime createDate;

    /**
     * The user who created the country.
     */
    private String createdBy;

    /**
     * The date the country was last updated.
     */
    private LocalDateTime lastUpdate;

    /**
     * The user who last updated the country.
     */
    private String lastUpdatedBy;

    /**
     * Gets the ID of the country.
     *
     * @return The ID of the country.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets the ID of the country.
     *
     * @param countryId The ID of the country.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Gets the name of the country.
     *
     * @return The name of the country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the name of the country.
     *
     * @param country The name of the country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the date the country was created.
     *
     * @return The date the country was created.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets the date the country was created.
     *
     * @param createDate The date the country was created.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the user who created the country.
     *
     * @return The user who created the country.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the country.
     *
     * @param createdBy The user who created the country.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the date the country was last updated.
     *
     * @return The date the country was last updated.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the date the country was last updated.
     *
     * @param lastUpdate The date the country was last updated.
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the user who last updated the country.
     *
     * @return The user who last updated the country.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated the country.
     *
     * @param lastUpdatedBy The user who last updated the country.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public String toString() {
        return country;
    }

}
