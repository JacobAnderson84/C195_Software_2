package anderson.c195.access;

import anderson.c195.helper.JDBC;
import anderson.c195.model.Contact;
import anderson.c195.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is used to access the users table from MySQL database.
 * */
public class UsersAccess {
    /** This method gets all users from the database.
     * @return userList
     * */
    public static ObservableList<User> getAllUsers(){
        ObservableList<User> userList = FXCollections.observableArrayList();
        try {
            String sql = "Select * from users";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                User user = new User(userId, userName, password);
                userList.add(user);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    /** This method authenticates a user upon login.
     * The user input on the login screen is checked in the database to make sure the username and password are correct.
     * @param username
     * @param inputPassword
     * @return userList
     * */
    public static ObservableList<User> authenticateUser(String username, String inputPassword){
        ObservableList<User> userList = FXCollections.observableArrayList();
        try {
            String sql = "Select * from users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, inputPassword);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                User user = new User(userId, userName, password);
                userList.add(user);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }



}

