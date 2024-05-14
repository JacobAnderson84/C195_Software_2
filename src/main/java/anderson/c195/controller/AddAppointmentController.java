package anderson.c195.controller;

import anderson.c195.access.AppointmentAccess;
import anderson.c195.access.ContactsAccess;
import anderson.c195.access.CustomerAccess;
import anderson.c195.access.UsersAccess;
import anderson.c195.helper.Helper;
//import anderson.c195.helper.Locale;
import anderson.c195.helper.TimeHelper;
import anderson.c195.model.Appointment;
import anderson.c195.model.Contact;
import anderson.c195.model.Customer;
import anderson.c195.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
/** This class is the controller for the AddAppointmentView.
 * */
public class AddAppointmentController implements Initializable {
    @FXML
    private TextField appointmentIdTxt;
    @FXML
    private TextField titleTxt;
    @FXML
    private TextField descriptionTxt;
    @FXML
    private TextField locationTxt;
    @FXML
    private ComboBox<Contact> contactDropBox;
    @FXML
    private TextField typeTxt;
    @FXML
    private DatePicker startDateCalender;
    @FXML
    private DatePicker endDateCalender;
    @FXML
    private ComboBox<LocalTime> startTimeDropBox;
    @FXML
    private ComboBox<LocalTime> endTimeDropBox;
    @FXML
    private ComboBox<Customer> customerIdDropBox;
    @FXML
    private ComboBox<User> userIdDropBox;

    /** This is the method to initialize the AddAppointmentView.
     * @param url
     * @param resourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
        //populating the combo boxes
        startTimeDropBox.setItems(TimeHelper.getLocalTimeStart());
        endTimeDropBox.setItems(TimeHelper.getLocalTimeEnd());
        contactDropBox.setItems(ContactsAccess.getAllContacts());
        customerIdDropBox.setItems(CustomerAccess.getAllCustomers());
        userIdDropBox.setItems(UsersAccess.getAllUsers());
    }

    /** This is the method to save an Appointment.
     * This method gets all the information from the text fields and combo boxes. It also calls on a method to validate the appointment times to ensure no scheduling conflicts occur.
     * @param actionEvent
     * */
    public void onActionSave(ActionEvent actionEvent) throws IOException, SQLException {
        System.out.println("Save button clicked");
        try {
            String title = titleTxt.getText();
            String description = descriptionTxt.getText();
            String location = locationTxt.getText();
            Contact contact = contactDropBox.getSelectionModel().getSelectedItem();
            int contactId = contact.getContactId();
            String type = typeTxt.getText();
            Customer customer = customerIdDropBox.getSelectionModel().getSelectedItem();
            int customerId = customer.getId();
            User user = userIdDropBox.getSelectionModel().getSelectedItem();
            int userId = user.getUserId();
            LocalDate startDate = startDateCalender.getValue();
            LocalTime startTime = startTimeDropBox.getSelectionModel().getSelectedItem();
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
            LocalDate endDate = endDateCalender.getValue();
            LocalTime endTime = endTimeDropBox.getSelectionModel().getSelectedItem();
            LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
            //Checking for Time overlaps  new is the appointment to be added...old is the appointment to be checked against.
            if (!AppointmentAccess.appointmentValidation(startDateTime, endDateTime, customerId)) {
                AppointmentAccess.insertAppointment(title, description, location, contactId, type, customerId, userId, startDateTime, endDateTime);
                Helper.changeView(actionEvent, "AppointmentView");
            }
        }
        catch(NullPointerException e){
            Helper.alertError("Please enter valid values in text fields.");
        }

    }
    /** This is the method to cancel the AddAppointmentView.
     * On action the user will be taken back to the AppointmentView.
     * @param actionEvent
     * */
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel button clicked");
        Helper.changeView(actionEvent, "AppointmentView");
    }
    /** This is the action event handler for the customerIdDropBox.
     * @param actionEvent
     * */
    public void customerIdDropBox(ActionEvent actionEvent) {
    }
    /** This is the action event handler for the userIdDropBox.
     * @param actionEvent
     * */
    public void userIdDropBox(ActionEvent actionEvent) {
    }
    /** This is the action event handler for the startDateCalender.
     * @param actionEvent
     * */
    public void startDateCalender(ActionEvent actionEvent) {
    }
    /** This is the action event handler for the endDateCalender.
     * @param actionEvent
     * */
    public void endDateCalender(ActionEvent actionEvent) {
    }
    /** This is the action event handler for the contactDropBox.
     * @param actionEvent
     * */
    public void contactDropBox(ActionEvent actionEvent) {
    }
    /** This is the action event handler for the startTimeDropBox.
     * @param actionEvent
     * */
    public void startTimeDropBox(ActionEvent actionEvent) {
    }
    /** This is the action event handler for the endTimeDropBox.
     * @param actionEvent
     * */
    public void endTimeDropBox(ActionEvent actionEvent) {
    }
}