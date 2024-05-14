package anderson.c195.controller;

import anderson.c195.access.*;
import anderson.c195.helper.Helper;
import anderson.c195.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Month;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

/** This class is the controller for the ReportsView.
 * */
public class ReportsController implements Initializable {
    //table columns for AppointmentByContactId Table
    @FXML
    private TableColumn appointmentIdByContact;
    @FXML
    private TableColumn titleByContactId;
    @FXML
    private TableColumn typeByContactId;
    @FXML
    private TableColumn descriptionByContactId;
    @FXML
    private TableColumn startDateByContactId;
    @FXML
    private TableColumn startTimeByContactId;
    @FXML
    private TableColumn endDateByContactId;
    @FXML
    private TableColumn endTimeByContactId;
    @FXML
    private TableColumn customerIdByContactId;

    //table columns for totalCustomersByCountryTable
    @FXML
    private TableColumn customerIdByCountry;
    @FXML
    private TableColumn nameByCountry;
    @FXML
    private TableColumn phoneNumberByCountry;
    @FXML
    private TableColumn addressByCountry;
    @FXML
    private TableColumn postalCodeByCountry;
    @FXML
    private TableColumn divisionByCountry;

    @FXML
    private ObservableList<Month> monthList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> typeList = FXCollections.observableArrayList();

    @FXML
    private RadioButton appointmentsByContactRadioButton;
    @FXML
    private RadioButton totalCustomersByMonthAndTypeRadioButton;
    @FXML
    private RadioButton totalCustomersByCountryRadioButton;
    @FXML
    private ComboBox<Contact> contactDropBox;
    @FXML
    private ComboBox<Month> monthDropBox;
    @FXML
    private ComboBox<String> typeDropBox;
    @FXML
    private ComboBox<Country> countryDropBox;
    @FXML
    private TableView appointmentByContactTable;
    @FXML
    private TableView totalCustomersByCountryTable;
    @FXML
    private HBox hBoxCustomersByMonthAndType;
    @FXML
    private Label amountOfCustomers;

    /** This is the method to initialize the ReportsView.
     * When the user selects a radio button it will set the correct table to be visible.
     * @param url
     * @param resourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
        contactDropBox.setItems(ContactsAccess.getAllContacts());
        monthList.addAll(Arrays.asList(Month.values()));
        monthDropBox.setItems(monthList);
        typeDropBox.setItems(getTypeList());
        countryDropBox.setItems(CountriesAccess.getAllCountries());
    }

    /** This method will take users back to the AppointmentView.
     * @param actionEvent
     * */
    public void onActionBack(ActionEvent actionEvent) throws IOException {
        System.out.println("Back button clicked");
        Helper.changeView(actionEvent, "AppointmentView");
    }

    /** This method will let the user select a contact from the Contact Drop box.
     * @param actionEvent
     * */
    public void appointmentsByContact(ActionEvent actionEvent) {
        System.out.println("Appointments by Contact Radio Button Clicked");
        setViewBooleans(true, false, false, false, true, true, true);
    }
    /** This method will take the user selection and show all appointment filtered by a Contact.
     * On action the table it set with appropriate items.
     * @param actionEvent
     * */
    public void contactDropBox(ActionEvent actionEvent) {
        Contact contactToSearch = contactDropBox.getSelectionModel().getSelectedItem();
        appointmentByContactTable.setItems(AppointmentAccess.getAllAppointmentByContact(contactToSearch.getContactId()));
        setAppointmentByContactTable();
    }

    /** This method will let the user select a Month and Type to search appointments.
     * @param actionEvent
     * */
    public void totalCustomersByMonthAndType(ActionEvent actionEvent) {
        System.out.println("Total Customers by Month and Type Radio Button Clicked");
        setViewBooleans(false, true, false, true, false, false, true);
    }

    /** This method will get the users selection from the monthDropBox adn then search by selection.
     * On action the selected month will be assigned a variable.
     * @param actionEvent */
    public void monthDropBox(ActionEvent actionEvent) {
        Month monthToSearch = monthDropBox.getSelectionModel().getSelectedItem();
        searchByMonthAndType();
    }

    /** This method will get the users selection from the typeDropBox and then search by selection.
     * On action the selected type will be assigned a variable.
     * @param actionEvent */
    public void typeDropBox(ActionEvent actionEvent) {
        String typeToSearch = typeDropBox.getSelectionModel().getSelectedItem();
        searchByMonthAndType();
    }

    /** This method will take the users selection from the typeDropBox and monthDropBox and display the total amount of customers.
     * This method will display the total amount of appointments by Month and Type.*/
    private void searchByMonthAndType (){
        Month monthToSearch = monthDropBox.getSelectionModel().getSelectedItem();
        String typeToSearch = typeDropBox.getSelectionModel().getSelectedItem();
        amountOfCustomers.setText(String.valueOf(AppointmentAccess.getAllAppointmentsByMonthAndType(monthToSearch, typeToSearch)));
    }

    /** This method will let users select a country from the countryDropBox.
     * @param actionEvent
     * */
    public void totalCustomersByCountry(ActionEvent actionEvent) {
        System.out.println("Total Customers by Country Radio Button Clicked");
        setViewBooleans(false, true, true, true, true, true, false);
    }

    /** This method will take the users selected country and display all customers for that country.
     * A table and the total amount of customers for the selected country will be shown.
     * @param actionEvent
     * */
    public void countryDropBox(ActionEvent actionEvent) {
        Country countryToSearch = countryDropBox.getSelectionModel().getSelectedItem();
        ObservableList<Division> divisionList = FirstLevelDivisionAccess.getAllDivisionsByCountryId(countryToSearch.getId());
        ObservableList<Customer> customersList = CustomerAccess.getAllCustomers();
        ObservableList<Customer> customersToShowList = FXCollections.observableArrayList();
        for(int i = 0; i < customersList.size(); i++){
            for(int j = 0; j < divisionList.size(); j++){
                if(Objects.equals(customersList.get(i).getDivision(), divisionList.get(j).getName())){
                    customersToShowList.add(customersList.get(i));
                }
            }
        }
        totalCustomersByCountryTable.setItems(customersToShowList);
        setCustomersByCountryTable();
        amountOfCustomers.setText(String.valueOf(customersToShowList.size()));
    }



    /** This method sets the table and combo boxes to disable and visible based on radio button selected.
     * Depending on the selection the correct combo boxes and table will be set to visible and allow the user to interact with them.
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @param g
     * */
    private void setViewBooleans(boolean a, boolean  b, boolean c, boolean d, boolean e, boolean f, boolean g) {
        appointmentByContactTable.setVisible(a);
        hBoxCustomersByMonthAndType.setVisible(b);
        totalCustomersByCountryTable.setVisible(c);
        contactDropBox.setDisable(d);
        monthDropBox.setDisable(e);
        typeDropBox.setDisable(f);
        countryDropBox.setDisable(g);
    }

    /** This method creates a list of all the different appointment types.
     * The type list is later used to populate the typeDropBox.
     * @return Returns typeList
     * */
    private ObservableList<String> getTypeList(){
        ObservableList<Appointment> tempAppointmentList = AppointmentAccess.getAllAppointments();
        for(int i = 0; i < tempAppointmentList.size(); i++) {
            typeList.add(tempAppointmentList.get(i).getType());
        }
        return typeList;
    }

    /** This method sets the appointmentByContactTable with the correct values.
     * Based on the selection from the contactDropBox. */
    private void setAppointmentByContactTable(){
        appointmentIdByContact.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleByContactId.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionByContactId.setCellValueFactory(new PropertyValueFactory<>("desc"));
        typeByContactId.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateByContactId.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startTimeByContactId.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endDateByContactId.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        endTimeByContactId.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customerIdByContactId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }

    /** This method sets the customersByCountryTable with the correct values.
     * Based on the selection from the countryDropBox.
     * */
    private void setCustomersByCountryTable(){
        customerIdByCountry.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameByCountry.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNumberByCountry.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressByCountry.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeByCountry.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        divisionByCountry.setCellValueFactory(new PropertyValueFactory<>("division"));
    }


}


