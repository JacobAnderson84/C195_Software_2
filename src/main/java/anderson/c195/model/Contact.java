package anderson.c195.model;

/** This is the class for Contact.
 * This contains methods for setting and getting information for Contacts.
 * */
public class Contact {
    private int contactId;
    private String contactName;
    private String contactEmail;
    /** This is the constructor for a Contact.
     * Constructor for a Contact object
     * */
    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }
    /** This is the getter for contactId.
     * @return contactId
     * */
    public int getContactId() {
        return contactId;
    }
    /** This is the setter for contactId.
     * @param contactId the contactId to set
     * */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /** This is the getter for contactName.
     * @return contactName
     * */
    public String getContactName() {
        return contactName;
    }
    /** This is the setter for contactName.
     * @param contactName the contactName to set
     * */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /** This is the getter for contactEmail.
     * @return contactEmail
     * */
    public String getContactEmail() {
        return contactEmail;
    }
    /** This is the setter for contactEmail.
     * @param contactEmail the contactEmail to set
     * */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    /** This method take the contactId and contactName and returns them as a String.
     * When interacting with the database the information returned is unreadable. This method return information in a readable format.
     * @return (contactId + " " + contactName)
     * */
    @Override
    public String toString() {
        return(contactId + " " + contactName);
    }
}
