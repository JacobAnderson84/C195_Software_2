package anderson.c195.controller;

import anderson.c195.access.AppointmentAccess;
import anderson.c195.helper.AlertError;
import anderson.c195.helper.Helper;
import anderson.c195.model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class is the controller for the AppointmentView.
 * */
public class AppointmentController implements Initializable {
    /** This is a lambda expression for adding an alertError box.
     * I used a lambda here because it helps reduce redundancy in my code.
     * */
    //Lambda Expression
    protected AlertError alertError = (e) -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(e);
        alert.showAndWait();
    };
    private LocalDate localDate = LocalDate.now();
    @FXML
    private TableColumn mainAppId;
    @FXML
    private TableColumn mainTitle;
    @FXML
    private TableColumn mainDescription;
    @FXML
    private TableColumn mainLocation;
    @FXML
    private TableColumn mainContact;
    @FXML
    private TableColumn mainType;
    @FXML
    private TableColumn mainStartDate;
    @FXML
    private TableColumn mainStartTime;
    @FXML
    private TableColumn mainEndDate;
    @FXML
    private TableColumn mainEndTime;
    @FXML
    private TableColumn mainCustomerId;
    @FXML
    private TableColumn mainUserId;
    @FXML
    private Label displayCurrentDate;
    @FXML
    private ToggleGroup appointmentByMonthAndWeek;
    @FXML
    private TableView appointmentTable;

    /** This is the method to initialize the AppointmentView.
     * @param url
     * @param resourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
        appointmentTable.setItems(AppointmentAccess.getAllAppointments());
        setTable();
        displayCurrentDate.setText(localDate.toString());
    }

    /** This is the action event handler for the ALlRadioButton.
     * On action the appointmentTable will be populated with all the appointment in database.
     * @param actionEvent
     * */
    public void onActionAllRadioButton(ActionEvent actionEvent) {
        System.out.println("All appointments button clicked");
        appointmentTable.setItems(AppointmentAccess.getAllAppointments());
        setTable();
    }

    /** This is the action event handler for the WeekRadioButton.
     * On action the appointmentTable will be populated with all the appointments  for the current week.
     * @param actionEvent
     * */
    public void onActionWeekRadioButton(ActionEvent actionEvent) {
        System.out.println("Appointments by week button clicked");
        LocalDate date = LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth());
        int weekOfYear = date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        appointmentTable.setItems(AppointmentAccess.getAllAppointmentsWeek(String.valueOf(weekOfYear), String.valueOf(localDate.getYear())));
        setTable();
    }

    /** This is the action event handler for the MonthRadioButton.
     * On action the appointmentTable will be populated with all the appointments  for the current month.
     * @param actionEvent
     * */
    public void onActionMonthRadioButton(ActionEvent actionEvent) {
        System.out.println("Appointments by month button clicked");
        appointmentTable.setItems(AppointmentAccess.getAllAppointmentsMonth(String.valueOf(localDate.getMonthValue())));
        setTable();
    }

    /** This method will take the user to the AddAppointmentView.
     * On action this changes views to the AddAppointmentView.
     * @param actionEvent
     * */
    public void onActionAddAppointment(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Appointments button clicked");
        Helper.changeView(actionEvent,"AddAppointmentView");
    }

    /** This method will take the user to the UpdateAppointmentView.
     * On action this changes views to the UpdateAppointmentView. The selectedAppointment will be sent to the UpdateAppointmentView so the fields and combo boxes can be populated with the correct information.
     * This method calls upon my Lambda Expression that is declared in this class. The lambda expression creates an alertError box. I used a lambda here to reduce redundancy in my code.
     * @param actionEvent
     * */
    public void onActionUpdateAppointment(ActionEvent actionEvent) throws IOException {
        System.out.println("Update Appointments button clicked");
        Appointment selectedAppointment = (Appointment) appointmentTable.getSelectionModel().getSelectedItem();
        //make sure there is a appointment selected
        if (selectedAppointment == null) {
//            Helper.alertError("Please select a appointment to update.");
            alertError.alertError("Please select an appointment to update.");
        }
        //preparing date to be sent and changing the view
        int index = appointmentTable.getSelectionModel().getSelectedIndex();
        UpdateAppointmentController.sendData(selectedAppointment, index);
        Helper.changeView(actionEvent, "UpdateAppointmentView");
    }

    /** This method will delete a selected Appointment.
     * There are some checks to make sure an appointment is selected and the user wants to delete it from the database.
     * @param actionEvent
     * */
    public void onActionDeleteAppointment(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("Delete Appointments button clicked");
        Appointment selectedAppointment = (Appointment) appointmentTable.getSelectionModel().getSelectedItem();
        if(selectedAppointment == null){
            Helper.alertError("Please Select an Appointment to Delete");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected Appointment?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                String alertInformation = "Appointment "  + selectedAppointment.getId() + " of type " + selectedAppointment.getType() + " was deleted.";
                AppointmentAccess.deleteAppointment(selectedAppointment.getId());
                Helper.alertInformation("Appointment Deletion Notification", alertInformation);
                Helper.changeView(actionEvent, "AppointmentView");
            }
        }
    }

    /** This method will take the user to the ReportsView.
     * On action this changes views to the ReportsView.
     * @param actionEvent
     * */
    public void onActionViewReports(ActionEvent actionEvent) throws IOException {
        System.out.println("View Reports button clicked");
        Helper.changeView(actionEvent, "ReportsView");
    }

    /** This method will take the user to the CustomersView.
     * On action this changes views to the CustomersView.
     * @param actionEvent
     * */
    public void onActionViewCustomers(ActionEvent actionEvent) throws IOException {
        System.out.println("View Customers button clicked");
        Helper.changeView(actionEvent, "CustomersView");
    }

    /** This method sets the AppointmentTable with the different values.
     * */
    private void setTable(){
        mainAppId.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        mainDescription.setCellValueFactory(new PropertyValueFactory<>("desc"));
        mainLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        mainContact.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        mainType.setCellValueFactory(new PropertyValueFactory<>("type"));
        mainStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        mainStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        mainEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        mainEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        mainCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        mainUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }


}