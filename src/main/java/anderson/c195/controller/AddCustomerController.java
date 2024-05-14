package anderson.c195.controller;

import anderson.c195.access.CountriesAccess;
import anderson.c195.access.CustomerAccess;
import anderson.c195.access.FirstLevelDivisionAccess;
import anderson.c195.helper.Helper;
import anderson.c195.model.Country;
import anderson.c195.model.Division;
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
import java.util.ResourceBundle;

/** This class is the controller for AddCustomerView.
 * */
public class AddCustomerController implements Initializable {

    @FXML
    private TextField customerIdTxt;
    @FXML
    private TextField customerNameTxt;
    @FXML
    private TextField customerAddressTxt;
    @FXML
    private TextField customerPostalCodeTxt;
    @FXML
    private ComboBox<Country> customerCountryDropBox;
    @FXML
    private ComboBox<Division> customerDivisionDropBox;
    @FXML
    private TextField customerPhoneNumberTxt;


    /** This is the method to initialize the AddCustomerView.
     * @param url
     * @param resourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
        ObservableList<Country> countries = CountriesAccess.getAllCountries();
        ObservableList<Division> divisions= FirstLevelDivisionAccess.getAllDivisions();

        customerCountryDropBox.setItems(countries);
        customerDivisionDropBox.setItems(divisions);
        customerDivisionDropBox.setDisable(true);
    }

    /** This Method is for the Country Dropbox action event.
     * This country takes the selection from Country drop box and populates the Division drop box with the proper divisions associated with the selected country
     * @param actionEvent
     */
    public void onActionCountryDropBox(ActionEvent actionEvent) {
        int countryId = customerCountryDropBox.getSelectionModel().getSelectedItem().getId();
        ObservableList<Division> divisions = FirstLevelDivisionAccess.getAllDivisions();
        ObservableList<Division> divisionByCountry = FXCollections.observableArrayList() ;

        for(int i = 0; i < divisions.size(); i++) {
            if(divisions.get(i).getCountryId() == countryId) {
                divisionByCountry.add(divisions.get(i));
            }
        }
        customerDivisionDropBox.setItems(divisionByCountry);
        customerDivisionDropBox.setDisable(false);

    }

    /** This is the method to save a Customer.
     * @param actionEvent
     * */
    public void onActionSave(ActionEvent actionEvent) throws IOException {
        try {
            String name = customerNameTxt.getText();
            String address = customerAddressTxt.getText();
            String postalCode = customerPostalCodeTxt.getText();
            String phone = customerPhoneNumberTxt.getText();
            int divisionId = customerDivisionDropBox.getSelectionModel().getSelectedItem().getId();

            CustomerAccess.insertCustomer(name, address, postalCode, phone, divisionId);
            Helper.changeView(actionEvent, "CustomersView");

        }
        catch (NullPointerException e) {
            System.out.println("Please enter valid values in text fields");
            Helper.alertError("Please enter valid values in text fields.");
       }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** This is the method to cancel on the AddCustomerView.
     * On action the user will be returned to the CustomersView.
     * @param actionEvent
     * */
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel button clicked");
        Helper.changeView(actionEvent, "CustomersView");
    }


}