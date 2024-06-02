package ha.scheduling.app.model;

/**
 * A class that represents a contact.
 */
public class Contact {

    /**
     * The ID of the contact.
     */
    private int contactID;

    /**
     * The name of the contact.
     */
    private String contactName;

    /**
     * The email address of the contact.
     */
    private String email;

    /**
     * Gets the ID of the contact.
     *
     * @return The ID of the contact.
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Sets the ID of the contact.
     *
     * @param contactID The ID of the contact.
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Gets the name of the contact.
     *
     * @return The name of the contact.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the name of the contact.
     *
     * @param contactName The name of the contact.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets the email address of the contact.
     *
     * @return The email address of the contact.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the contact.
     *
     * @param email The email address of the contact.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return contactName;
    }
}
