package anderson.c195.access;

import anderson.c195.helper.JDBC;
import anderson.c195.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static anderson.c195.access.FirstLevelDivisionAccess.getCountryId;

/** This class is used to access the countries table from MySQL database.
 * */
public class CountriesAccess {
    /** This method gets all countries from the database.
     * @return countriesList
     * */
    public static ObservableList<Country> getAllCountries(){
        ObservableList<Country> countriesList = FXCollections.observableArrayList();
        try{
            String sql = "Select * from countries";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Country country = new Country(countryId, countryName);
                countriesList.add(country);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countriesList;
    }

    /** This method gets a country's name from the database.
     * @param divisionId
     * @return countryName
     * */
    public static String getCountryName(int divisionId){
        int countryId = getCountryId(divisionId);
        String countryName = null;
        try{
            String sql = "Select Country from countries where Country_ID = " + countryId;
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                countryName = rs.getString("Country");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countryName;
    }

}
