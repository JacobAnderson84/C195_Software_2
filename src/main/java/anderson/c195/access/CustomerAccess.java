package anderson.c195.access;

import anderson.c195.helper.JDBC;
import anderson.c195.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static anderson.c195.access.CountriesAccess.getCountryName;
import static anderson.c195.access.FirstLevelDivisionAccess.getDivisionName;

/** This class is used to access the customers table from MySQL database.
 * */
public class CustomerAccess {
    /** This method gets all customers from the database.
     * @return customersList
     * */
    public static ObservableList<Customer> getAllCustomers(){
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try{
            String sql = "Select * from customers";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionId = rs.getInt("Division_ID");

                String divisionName = getDivisionName(divisionId);
                String countryName = getCountryName(divisionId);

                Customer customer = new Customer(customerId, name, address, postalCode, countryName, divisionName, phone);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    /** This method inserts a new customer into the database.
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     * @return rowsAffected
     * */
    public static int insertCustomer(String customerName, String address, String postalCode, String phone, int divisionId) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5,divisionId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method updates a selected customer in the database.
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     * @return rowsAffected
     * */
    public static int updateCustomer(int customerId, String customerName, String address, String postalCode, String phone, int divisionId) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_Id = ?  WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5,divisionId);
        ps.setInt(6,customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method deletes a selected customer from the database.
     * @param customerId
     * @return rowsAffected
     * */
    public static int deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

}
