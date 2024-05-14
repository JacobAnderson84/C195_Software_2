package anderson.c195;

import anderson.c195.helper.JDBC;
import anderson.c195.helper.UserLocale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**This class creates a Scheduling management application.
 * JavaDocs location ---  src/JavaDocs
 * README location --- src/README
 * @author Jacob Anderson
 * */
public class Main extends Application {
    /** This brings up the LoginView.
     * */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("DB client app");
        stage.setScene(scene);
        stage.show();
    }

    /**This is the main method.
     * */
    public static void main(String[] args) throws SQLException {
        //sets local machine to french
        //Locale.setDefault(new Locale("fr", "FR"));
        //System.getProperties();
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();
    }
}