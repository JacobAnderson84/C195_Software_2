package anderson.c195.helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Optional;

/** This is the class for Helper.
 * This class contains different methods to help reduce redundancy throughout the program.
 * */
public class Helper {
    /** This method is for changing views throughout the program.
     * This method is called upon whenever a view is changed.
     * @param actionEvent actionEvent
     * @param viewName The view to change to
     * */
    public static void changeView(ActionEvent actionEvent, String viewName) throws IOException {
        Parent root = FXMLLoader.load(Helper.class.getResource("/anderson/c195/" + viewName + ".fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 996, 630);
        stage.setTitle("DB client app");
        stage.setScene(scene);
        stage.show();
    }
    /** This method is for creating alert ERROR notifications.
     * This method is called upon whenever an alert of Error type is required.
     * @param alertText The text to be displayed in the alert box
     * */
    public static void alertError(String alertText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(alertText);
        alert.showAndWait();
    }
    /** This method is for creating alert INFORMATION notifications.
     * This method is called upon whenever an alert of Information type is required.
     * @param alertTitle The title for the alert box
     * @param alertText  The text to be displayed in the alert box
     * */
    public static void alertInformation(String alertTitle, String alertText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(alertTitle);
        alert.setContentText(alertText);
        alert.showAndWait();
    }

//    public static void alertConfirmation(String alertText) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, alertText);
//        Optional<ButtonType> result = alert.showAndWait();
//    }

}
