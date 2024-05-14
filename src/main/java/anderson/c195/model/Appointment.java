package anderson.c195.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**This is the class for Appointment.
 * This contains methods for setting and getting information for Appointments.
 * */
public class Appointment {
    private int id;
    private String title;
    private String desc;
    private String location;
    private int contactId;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerId;
    private int userId;

    /**This is the constructor for an Appointment.
     * Constructor for an Appointment object 
     * */
    public Appointment(int id, String title, String desc, String location, int contactId, String type, LocalDateTime start,  LocalDateTime end, int customerId, int userId) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.location = location;
        this.contactId = contactId;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
    }

    /** This is the getter for id field.
     * @return Returns id
     * */
    public int getId() {
        return id;
    }
    /** This is the setter for id field.
     * @param id The id to set
     * */
    public void setId(int id) {
        this.id = id;
    }
    /** This is the getter for title field.
     * @return Returns title
     * */
    public String getTitle() {
        return title;
    }
    /** This is the setter for title field.
     * @param title The title to set
     * */
    public void setTitle(String title) {
        this.title = title;
    }
    /** This is the getter for desc field.
     * @return Returns desc
     * */
    public String getDesc() {
        return desc;
    }
    /** This is the setter for desc field.
     * @param desc The desc to set
     * */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    /** This is the getter for location field.
     * @return Returns location
     * */
    public String getLocation() {
        return location;
    }
    /** This is the setter for location field.
     * @param location The location to set
     * */
    public void setLocation(String location) {
        this.location = location;
    }
    /** This is the getter for contact field.
     * @return Returns contactId
     * */
    public int getContactId() {
        return contactId;
    }
    /** This is the setter for contactId field.
     * @param contactId The contactId to set
     * */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /** This is the getter for type field.
     * @return Returns type
     * */
    public String getType() {
        return type;
    }
    /** This is the setter for type field.
     * @param type The type to set
     * */
    public void setType(String type) {
        this.type = type;
    }
    /** This is the getter for start field.
     * @return Returns start
     * */
    public LocalDateTime getStart() {
        return start;
    }
    /** This is the setter for start field.
     * @param start The start to set
     * */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    /** This is the getter for end field.
     * @return Returns end
     * */
    public LocalDateTime getEnd() {
        return end;
    }
    /** This is the setter for end field.
     * @param end The end to set
     * */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    /** This is the getter for startDate field.
     * @return Returns startDate
     * */
    public LocalDate getStartDate() {
        return start.toLocalDate();
    }
    /** This is the getter for startTime field.
     * @return Returns startTime
     * */
    public LocalTime getStartTime() {
        return start.toLocalTime();
    }
    /** This is the getter for endDate field.
     * @return Returns endDate
     * */
    public LocalDate getEndDate() {
        return end.toLocalDate();
    }
    /** This is the getter for endTime field.
     * @return Returns endTime
     * */
    public LocalTime getEndTime() {
        return end.toLocalTime();
    }
    /** This is the getter for customerId field.
     * @return Returns customerId
     * */
    public int getCustomerId() {
        return customerId;
    }
    /** This is the setter for customerId field.
     * @param customerId The customerId to set
     * */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /** This is the getter for userId field.
     * @return Returns userId
     * */
    public int getUserId() {
        return userId;
    }
    /** This is the setter for userId field.
     * @param userId The userId to set
     * */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
