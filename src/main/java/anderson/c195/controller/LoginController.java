package anderson.c195.controller;

import anderson.c195.access.AppointmentAccess;
import anderson.c195.access.UsersAccess;
import anderson.c195.helper.Helper;
import anderson.c195.helper.UserLocale;
import anderson.c195.helper.ZoneID;
import anderson.c195.model.Appointment;
import anderson.c195.model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/** This class is the controller for the LoginView.
 * */
public class LoginController implements Initializable {
    /** This is a lambda for setting the zoneId.
     * I used a lambda expression here because it helps reduce methods and lines of code written throughout the program.
     * */
    //java doc comment for lambda expression
    protected ZoneID lambda = () -> ZoneId.systemDefault().toString();

    @FXML
    private Label currentLocation;
    @FXML
    private TextField usernameTxt;
    @FXML
    private TextField passwordTxt;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Label locationLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private ResourceBundle rb = ResourceBundle.getBundle("Lang", Locale.getDefault());

    /** This method logs in a user to the program.
     * The user enters in password and username that is checked in the database. Login information is recorded in the login.activity.txt file.
     * An alert is shown to inform the users if there are appointments in the next fifteen minutes or not.
     * @param actionEvent
     * */
    @FXML
    protected void onActionLogin(ActionEvent actionEvent) throws IOException {
        System.out.println("Login Button Clicked");
        System.out.println("Save button clicked");
        boolean userFound = false;
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
        if(UsersAccess.authenticateUser(username, password).size() == 0){
             System.out.println("Incorrect username or password");
             Helper.alertError("Incorrect Username or Password");
        }
        else {
            LocalDateTime timeNow = LocalDateTime.now();
            LocalDateTime timeNowPlus15 = timeNow.plusMinutes(15);
            ObservableList<Appointment> tempList = AppointmentAccess.getAllAppointments();
            boolean fifteenMinuteAlert = false;
            for (int i = 0; i < tempList.size(); i++) {
                LocalDateTime start = tempList.get(i).getStart();
                if ((start.isEqual(timeNow) || start.isAfter(timeNow)) && (start.isEqual(timeNowPlus15) || start.isBefore(timeNowPlus15))) {
                    String alertInformation = "Appointment: " + tempList.get(i).getId() + " at " + tempList.get(i).getStart() + " begins soon.";
                    Helper.alertInformation("Appointment Notification", alertInformation);
                    fifteenMinuteAlert = true;
                } else {
                    fifteenMinuteAlert = false;
                }
            }
            if (!fifteenMinuteAlert){
                Helper.alertInformation("Appointment Notification", "There are no appointments upcoming in the next fifteen minutes.");
            }
                Helper.changeView(actionEvent, "AppointmentView");
            userFound = true;
        }
        //Locale.setDefault(new Locale("en", "US"));

        //Login Activity
        String filename = "login_activity.txt";
        LocalDateTime loginDateTime = LocalDateTime.now();
        String loginSuccessful;
        FileWriter loginActivityFile = new FileWriter(filename, true);
        PrintWriter loginActivityPrint = new PrintWriter(loginActivityFile);
        if (userFound){
            loginSuccessful = "Success";
        }
        else {
            loginSuccessful = "Unsuccessful";
        }
        loginActivityPrint.print("Username: " + username + "    Password: " + password + "    Date and Time: ");
        loginActivityPrint.print(loginDateTime);
        loginActivityPrint.print("    " + loginSuccessful);
        loginActivityPrint.println(" ");
        loginActivityPrint.close();

    }

    /** This is the method to initialize the LoginView.
     * @param url
     * @param resourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
        UserLocale.setLocale();
        System.out.println(UserLocale.getLocale());
        //currentLocation.setText(ZoneId.systemDefault().toString());
        currentLocation.setText(lambda.displayZoneId());
        usernameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
        locationLabel.setText(rb.getString("location"));
        loginButton.setText(rb.getString("login"));
        cancelButton.setText(rb.getString("cancel"));
    }

    /** This method will clear the text fields where the user enters userName and password.
     * @param actionEvent
     * */
    public void onActionCancel(ActionEvent actionEvent) {
        usernameTxt.clear();
        passwordTxt.clear();
    }
}