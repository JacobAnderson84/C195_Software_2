package anderson.c195.access;

import anderson.c195.helper.JDBC;
import anderson.c195.model.Contact;
import anderson.c195.model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is used to access the contacts table from MySQL database.
 * */
public class ContactsAccess {
    /** This method gets all contacts from the database.
     * @return contactList
     * */
    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        try {
            String sql = "Select * from contacts";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contact contact = new Contact(contactId, contactName, email);
                contactList.add(contact);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contactList;
    }

}
