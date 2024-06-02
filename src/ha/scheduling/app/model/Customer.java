package ha.scheduling.app.model;

import java.time.LocalDateTime;

/**
 * A class that represents a customer.
 */
public class Customer {

    /**
     * The ID of the customer.
     */
    private int customerId;

    /**
     * The name of the customer.
     */
    private String customerName;

    /**
     * The address of the customer.
     */
    private String address;

    /**
     * The postal code of the customer.
     */
    private String postalCode;

    /**
     * The phone number of the customer.
     */
    private String phone;

    /**
     * The date the customer was created.
     */
    private LocalDateTime createDate;

    /**
     * The user who created the customer.
     */
    private String createdBy;

    /**
     * The date the customer was last updated.
     */
    private LocalDateTime lastUpdate;

    /**
     * The user who last updated the customer.
     */
    private String lastUpdatedBy;

    /**
     * The ID of the division the customer belongs to.
     */
    private int divisionId;

    /**
     * Gets the ID of the customer.
     *
     * @return The ID of the customer.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the ID of the customer.
     *
     * @param customerId The ID of the customer.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the name of the customer.
     *
     * @param customerName The name of the customer.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the address of the customer.
     *
     * @return The address of the customer.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer.
     *
     * @param address The address of the customer.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the postal code of the customer.
     *
     * @return The postal code of the customer.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the customer.
     *
     * @param postalCode The postal code of the customer.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the phone number of the customer.
     *
     * @return The phone number of the customer.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param phone The phone number of the customer.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the date the customer was created.
     *
     * @return The date the customer was created.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets the date the customer was created.
     *
     * @param createDate The date the customer was created.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the user who created the customer.
     *
     * @return The user who created the customer.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    @Override
    public String toString() {
        return String.format("%d - %s", customerId, customerName);
    }

}
