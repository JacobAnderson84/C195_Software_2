package anderson.c195.access;

import anderson.c195.helper.JDBC;
import anderson.c195.model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is used to access the first_level_divisions table from MySQL database.
 * */
public class FirstLevelDivisionAccess {
    /** This method gets all divisions from the database.
     * @return divisionList
     * */
    public static ObservableList<Division> getAllDivisions(){
        ObservableList<Division> divisionsList = FXCollections.observableArrayList();
        try {
            String sql = "Select * from first_level_divisions";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                Division division = new Division(divisionId, divisionName, countryId);
                divisionsList.add(division);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return divisionsList;
    }

    /** This method gets a division's name from database.
     * @param divisionId
     * @return divisionName
     * */
    public static String getDivisionName(int divisionId) {
        String divisionName = null;
        try {
            String sql = "Select Division from first_level_divisions where Division_ID = " + divisionId;
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                divisionName = rs.getString("Division");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return divisionName;
    }

    /** This method gets a country's Id from database.
     * @param divisionId
     * @return countryId
     * */
    public static int getCountryId(int divisionId){
        int countryId = 0;

        try {
            String sql = "Select Country_ID from first_level_divisions where Division_ID = " + divisionId;
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                countryId = rs.getInt("Country_ID");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countryId;
    }

    /** This method gets all divisions from a selected country.
     * @param countryIdToSearch
     * @return divisionsList
     * */
    public static ObservableList<Division> getAllDivisionsByCountryId(int countryIdToSearch){
        ObservableList<Division> divisionsList = FXCollections.observableArrayList();
        try {
            String sql = "Select * from first_level_divisions WHERE Country_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, countryIdToSearch);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                Division division = new Division(divisionId, divisionName, countryId);
                divisionsList.add(division);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return divisionsList;
    }
}
