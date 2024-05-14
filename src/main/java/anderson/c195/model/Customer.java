package anderson.c195.model;

import anderson.c195.access.AppointmentAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This is the class for Customer.
 * This contains the setters and getters for a Customer object.
 * */
public class Customer {
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String country;
    private String divisionName;
    private String phoneNumber;
    private ObservableList<Appointment> associatedAppointment = FXCollections.observableArrayList();
    /** This is the constructor for a Customer.
     * Constructor for a Customer object */
    public Customer(int id, String name, String address, String postalCode, String country, String divisionName, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.divisionName = divisionName;
        this.phoneNumber = phoneNumber;
    }
    /** This is the getter for id.
     * @return Returns id
     * */
    public int getId() {
        return id;
    }
    /** This is the setter for id.
     * @param id The id to set
     * */
    public void setId(int id) {
        this.id = id;
    }
    /** This is the getter for name.
     * @return Returns name
     * */
    public String getName() {
        return name;
    }
    /** This is the setter for name.
     * @param name The name to set
     * */
    public void setName(String name) {
        this.name = name;
    }
    /** This is the getter for address.
     * @return Returns address
     * */
    public String getAddress() {
        return address;
    }
    /** This is the setter for address.
     * @param address The address to set
     * */
    public void setAddress(String address) {
        this.address = address;
    }
    /** This is the getter for postalCode.
     * @return Returns postalCode
     * */
    public String getPostalCode() {
        return postalCode;
    }
    /** This is the setter for postalCode.
     * @param postalCode The postalCode to set
     * */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    /** This is the getter for country.
     * @return Returns country
     * */
    public String getCountry() {
        return country;
    }
    /** This is the setter for country.
     * @param country The country to set
     * */
    public void setCountry(String country) {
        this.country = country;
    }
    /** This is the getter for division.
     * @return Returns divisionName
     * */
    public String getDivision() {
        return divisionName;
    }
    /** This is the setter for divisionName.
     * @param division The divisionName to set
     * */
    public void setDivision(String division) {
        this.divisionName = divisionName;
    }
    /** This is the getter for phoneNumber.
     * @return Returns phoneNumber
     * */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /** This is the setter for phoneNumber.
     * @param phoneNumber The phoneNumber to set
     * */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /** This method gets all associatedAppointments for a customer.
     * When deleting a customer from the database the program needs to check if the customer has any appointments scheduled. This method checks for any appointments.
     * @param customerId The customerId to check
     * @return Returns associatedAppointment*/
    public ObservableList<Appointment> getAllAssociatedAppointments(int customerId) {
        ObservableList<Appointment> associatedAppointment = AppointmentAccess.getAllAppointmentByCustomerId(customerId);
        return associatedAppointment;
    }
    /** This method returns some customer information as a String.
     * This method takes a customers id and name and turns it into a String. This is for displaying in combo boxes within the application.
     * @return Returns (id + " " + name)
     * */
    @Override
    public String toString() {
        return(id + " " + name);
    }
}
