package anderson.c195.controller;

import anderson.c195.access.CustomerAccess;
import anderson.c195.helper.Helper;
import anderson.c195.model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class is the controller for CustomersView.
 * */
public class CustomersController implements Initializable {
    @FXML
    private TableColumn customerId;
    @FXML
    private TableColumn customerName;
    @FXML
    private TableColumn customerPhoneNumber;
    @FXML
    private TableColumn customerAddress;
    @FXML
    private TableColumn customerPostalCode;
    @FXML
    private TableColumn customerDivision;
    @FXML
    private TableColumn customerCountry;
    @FXML
    private TableView customersTable;

    /** This is the method to initialize the CustomerView.
     * @param url
     * @param resourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
        customersTable.setItems(CustomerAccess.getAllCustomers());
        customerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerDivision.setCellValueFactory(new PropertyValueFactory<>("division"));
        customerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
    }

    /** This method will take the user to the AddCustomerView.
     * On action this changes views to the AddCustomerView.
     * @param actionEvent
     * */
    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Customer button clicked");
        Helper.changeView(actionEvent, "AddCustomerView");
    }

    /** This method will take the user to the ReportsView.
     * On action this changes views to the ReportsView.
     * @param actionEvent
     * */
    public void onActionViewReports(ActionEvent actionEvent) throws IOException {
        System.out.println("View Reports button clicked");
        Helper.changeView(actionEvent, "ReportsView");
    }

    /** This method will delete a selected Customer.
     * There are some checks to make sure a customer is selected and the user wants to delete it from the database.
     * There is another check to make sure the customer has no associated appointments before deletion.
     * @param actionEvent
     * */
    public void onActionDeleteCustomer(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("Delete Customer button clicked");
        //selecting a customer
        Customer selectedCustomer = (Customer) customersTable.getSelectionModel().getSelectedItem();
        if(selectedCustomer == null){
            Helper.alertError("Please select a customer to delete.");
        }
        else {
            //making sure the selected customer has no appointments associated with it
            if (selectedCustomer.getAllAssociatedAppointments(selectedCustomer.getId()).size() != 0) {
                Helper.alertError("Cannot delete because Customer has scheduled appointments");
            }
            else {
//            Helper.alertConfirmation("Are you sure you want to delete the selected Customer?");
                //confirmation before deletion
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected Customer?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    CustomerAccess.deleteCustomer(selectedCustomer.getId());
                    Helper.changeView(actionEvent, "CustomersView");
                }
            }
        }
    }

    /** This method will take users to the UpdateCustomerView.
     * The selected Customer is sent to the UpdateCustomerView so that the correct fields can be populated with the data from the selected customer.
     * @param actionEvent
     * */
    public void onActionUpdateCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("Update Customer button clicked");
        //selecting Customer
        Customer selectedCustomer = (Customer) customersTable.getSelectionModel().getSelectedItem();
        //make sure there is a customer selected
        if (selectedCustomer == null) {
            Helper.alertError("Please select a customer to update.");
        }
        //preparing date to be sent and changing the view
        int index = customersTable.getSelectionModel().getSelectedIndex();
        UpdateCustomerController.sendData(selectedCustomer, index);
        Helper.changeView(actionEvent, "UpdateCustomerView");
    }

    /** This method will take users back to the AppointmentView.
     * @param actionEvent
     * */
    public void onActionBack(ActionEvent actionEvent) throws IOException {
        System.out.println("Back button clicked");
        Helper.changeView(actionEvent, "AppointmentView");
    }

}