package anderson.c195.controller;

import anderson.c195.access.AppointmentAccess;
import anderson.c195.access.ContactsAccess;
import anderson.c195.access.CustomerAccess;
import anderson.c195.access.UsersAccess;
import anderson.c195.helper.Helper;
import anderson.c195.helper.TimeHelper;
import anderson.c195.model.*;
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

/** This class is the controller for UpdateAppointmentView.
 * */
public class UpdateAppointmentController implements Initializable {
    private static Appointment appointmentToUpdate;
    private static int appointmentToUpdateIndex;

    @FXML
    private TextField appointmentIdTxt;
    @FXML
    private TextField titleTxt;
    @FXML
    private TextField descriptionTxt;
    @FXML
    private TextField locationTxt;
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
    private ComboBox<Contact> contactDropBox;
    @FXML
    private ComboBox<Customer> customerIdDropBox;
    @FXML
    private ComboBox<User> userIdDropBox;

    /** This is the method to initialize the UpdateAppointmentView.
     * Based on the selected appointment the text fields and combo boxes are populated with the correct values.
     * @param url
     * @param resourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
        //receiving appointment to update
        appointmentIdTxt.setText(String.valueOf(appointmentToUpdate.getId()));
        //setting text fields
        titleTxt.setText(appointmentToUpdate.getTitle());
        descriptionTxt.setText(appointmentToUpdate.getDesc());
        locationTxt.setText(appointmentToUpdate.getLocation());
        typeTxt.setText(appointmentToUpdate.getType());
        //setting combo boxes
        customerIdDropBox.setItems(CustomerAccess.getAllCustomers());
        userIdDropBox.setItems(UsersAccess.getAllUsers());
        startTimeDropBox.setItems(TimeHelper.getLocalTimeStart());
        endTimeDropBox.setItems(TimeHelper.getLocalTimeEnd());
        contactDropBox.setItems(ContactsAccess.getAllContacts());
        //setting combo boxes and calendars with the correct values of sent appointment
        int customerIdToDisplay = appointmentToUpdate.getCustomerId();
        for(Customer c : customerIdDropBox.getItems()){
            if(customerIdToDisplay == c.getId()){
                customerIdDropBox.setValue(c);
                break;
            }
        }
        int userIdToDisplay = appointmentToUpdate.getUserId();
        for(User u : userIdDropBox.getItems()){
            if(userIdToDisplay == u.getUserId()){
                userIdDropBox.setValue(u);
                break;
            }
        }
        int contactToDisplay = appointmentToUpdate.getContactId();
        for(Contact c : contactDropBox.getItems()){
            if(contactToDisplay == c.getContactId()){
                contactDropBox.setValue(c);
                break;
            }
        }
        startDateCalender.setValue(appointmentToUpdate.getStartDate());
        endDateCalender.setValue(appointmentToUpdate.getEndDate());
        startTimeDropBox.setValue(appointmentToUpdate.getStartTime());
        endTimeDropBox.setValue(appointmentToUpdate.getEndTime());

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

    /** This is the method to save an Appointment.
     * This method gets all the information from the text fields and combo boxes. It also calls on a method to validate the appointment times to ensure no scheduling conflicts occur.
     * @param actionEvent
     * */
    public void onActionSave(ActionEvent actionEvent) throws IOException {
        System.out.println("Save button clicked");
        try{
            int id = Integer.parseInt(appointmentIdTxt.getText());
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
            ObservableList<Appointment> tempList = AppointmentAccess.getAllAppointmentByCustomerId(customerId);
            LocalDateTime newStart = startDateTime;
            LocalDateTime newEnd = endDateTime;
            boolean overlap = false;
            for(int i = 0; i < tempList.size(); i++){
                LocalDateTime oldStart = tempList.get(i).getStart();
                LocalDateTime oldEnd = tempList.get(i).getEnd();
                //case 1
                if (((newStart.isAfter(oldStart) || newStart.isEqual(oldStart)) && (newStart.isBefore(oldEnd))) && id != tempList.get(i).getId()){
                    //case 1 error
                    Helper.alertError("This appointment overlaps with another appointment. Please select a different time.");
                    overlap = true;
                    break;
                }
                //case 2
                else if (((newEnd.isAfter(oldStart)) && ((newEnd.isBefore(oldEnd)) || newEnd.isEqual(oldEnd))) && id != tempList.get(i).getId()){
                    //case 2 error
                    Helper.alertError("This appointment overlaps with another appointment. Please select a different time.");
                    overlap = true;
                    break;
                }
                //case 3
                else if (((newStart.isBefore(oldStart) || newStart.isEqual(oldStart)) && (newEnd.isAfter(oldEnd) || newEnd.isEqual(oldEnd))) && id != tempList.get(i).getId()){
                    //case 3 error
                    Helper.alertError("This appointment overlaps with another appointment. Please select a different time.");
                    overlap = true;
                    break;
                }
                //case that the start time and end time are the same
                else if (newStart.isEqual(newEnd)){
                    Helper.alertError("The start time and end time cannot be the same. Please select different times.");
                    overlap = true;
                    break;
                }
                //case that end times/dates are before start times/dates
                else if (endDateTime.isBefore(startDateTime) || endDateTime.isEqual(startDateTime)){
                    Helper.alertError("The end date/time is before the start date/time. Please select different date/times");
                    overlap = true;
                    break;
                }
                else{
                    //no appointment overlap
                    overlap = false;
                }
            }
            if(!overlap){
                AppointmentAccess.updateAppointment(id, title, description, location, contactId, type, customerId, userId, startDateTime, endDateTime);
                Helper.changeView(actionEvent, "AppointmentView");
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (NumberFormatException e) {
            System.out.println("Please enter valid values in text fields");
            Helper.alertError("Please enter valid values in text fields");
        }
    }

    /** This is the method to cancel the UpdateAppointmentView.
     * On action the user will be taken back to the AppointmentView.
     * @param actionEvent
     * */
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel button clicked");
        Helper.changeView(actionEvent, "AppointmentView");
    }

    /** This method is used to receive the selected Appointment from the AppointmentView.
     * @param a The selected appointment
     * @param index The index of the selected appointment
     * */
    public static void sendData(Appointment a, int index){
        appointmentToUpdate = a;
        appointmentToUpdateIndex = index;
    }

    /** This method handles the action event for the customerIdDropBox.
     * @param actionEvent
     * */
    public void customerIdDropBox(ActionEvent actionEvent) {
    }
    /** This method handles the action event for the userIdDropBox.
     * @param actionEvent
     * */
    public void userIdDropBox(ActionEvent actionEvent) {
    }
}