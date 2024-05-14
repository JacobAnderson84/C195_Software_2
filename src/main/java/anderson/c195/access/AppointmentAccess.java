package anderson.c195.access;

import anderson.c195.helper.Helper;
import anderson.c195.helper.JDBC;
import anderson.c195.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

/** This class is used to access the appointments table from MySQL database.
 * */
public class AppointmentAccess {
    /** This method returns all appointments from the database.
     * @return appointmentList
     * */
    public static ObservableList<Appointment> getAllAppointments(){
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * from appointments";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int appId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String desc = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
//                Time startTime = rs.getTime("Start");
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
//                Time endTime = rs.getTime("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                Appointment app = new Appointment(appId, title, desc,location,contactId, type, start, end, customerId, userId);
                appointmentList.add(app);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return appointmentList;
    }
    /** This method inserts a new appointment into the database.
     * @param title
     * @param description
     * @param location
     * @param contactId
     * @param type
     * @param customerId
     * @param endDateTime
     * @param startDateTime
     * @param userId
     * @return rowsAffected
     * */
    public static int insertAppointment(String title, String description, String location, int contactId, String type, int customerId, int userId, LocalDateTime startDateTime, LocalDateTime endDateTime) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(startDateTime));
        ps.setTimestamp(6, Timestamp.valueOf(endDateTime));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method updates a selected appointment in the database.
     * @param appointmentId
     * @param title
     * @param description
     * @param location
     * @param contactId
     * @param type
     * @param customerId
     * @param userId
     * @param startDateTime
     * @param endDateTime
     * @return rowsAffected
     * */
    public static int updateAppointment(int appointmentId,String title, String description, String location, int contactId, String type, int customerId, int userId, LocalDateTime startDateTime, LocalDateTime endDateTime) throws SQLException{
        String sql = "UPDATE appointments Set Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, end = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(startDateTime));
        ps.setTimestamp(6, Timestamp.valueOf(endDateTime));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        ps.setInt(10, appointmentId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method deletes an appointment from the database.
     * @param appointmentId
     * @return rowsAffected
     * */
    public static int deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method gets all appointments by a month.
     * @param month
     * @return appointmentList
     * */
    public static ObservableList<Appointment> getAllAppointmentsMonth(String month){
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try{
            String sql = "select * from appointments Where Month(Start) = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, month);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int appId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String desc = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
//                Time startTime = rs.getTime("Start");
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
//                Time endTime = rs.getTime("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                Appointment app = new Appointment(appId, title, desc,location,contactId, type, start, end, customerId, userId);
                appointmentList.add(app);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return appointmentList;
    }

    /** This method gets all appointments by week.
     * @param week
     * @param year
     * @return appointmentList
     * */
    public static ObservableList<Appointment> getAllAppointmentsWeek(String week, String year){
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try{
            String sql = "select * from appointments Where week(Start) = ? AND year(Start) = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, week);
            ps.setString(2, year);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int appId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String desc = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
//                Time startTime = rs.getTime("Start");
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
//                Time endTime = rs.getTime("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                Appointment app = new Appointment(appId, title, desc,location,contactId, type, start, end, customerId, userId);
                appointmentList.add(app);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return appointmentList;
    }

    /** This method gets all appointments by contact.
     * @param contactId
     * @return appointmentList
     * */
    public static ObservableList<Appointment> getAllAppointmentByContact(int contactId){
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try{
            String sql = "select * from appointments Where Contact_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, contactId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int appId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String desc = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
//                Time startTime = rs.getTime("Start");
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
//                Time endTime = rs.getTime("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                Appointment app = new Appointment(appId, title, desc,location,contactId, type, start, end, customerId, userId);
                appointmentList.add(app);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentList;
    }

    /** This method gets all appointments by month and type.
     * @param month
     * @param type
     * @return appointmentList.size()
     * */
    public static int getAllAppointmentsByMonthAndType(Month month, String type){
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        int monthNumber = month.getValue();
        try {
            String sql = "SELECT * FROM appointments WHERE Month(Start) = ? AND Type = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, monthNumber);
            ps.setString(2, type);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int appId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String desc = rs.getString("Description");
                String location = rs.getString("Location");
//                String type = rs.getString("Type");
                int contactId = rs.getInt("Contact_ID");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
//                Time startTime = rs.getTime("Start");
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
//                Time endTime = rs.getTime("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                Appointment app = new Appointment(appId, title, desc,location,contactId, type, start, end, customerId, userId);
                appointmentList.add(app);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentList.size();

    }

    /** This method gets all appointments by customerId.
     * @param customerId
     * @return appointmentList
     * */
    public static ObservableList<Appointment> getAllAppointmentByCustomerId(int customerId){
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try{
            String sql = "select * from appointments Where Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int appId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String desc = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
//                Time startTime = rs.getTime("Start");
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
//                Time endTime = rs.getTime("End");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointment app = new Appointment(appId, title, desc,location,contactId, type, start, end, customerId, userId);
                appointmentList.add(app);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentList;
    }

    /** This method is used for appointment validation.
     * This method will take the user data/time inputs and make sure there are no appointment overlaps, and the end date and times are not before the start.
     * @param start
     * @param end
     * @param customerId
     * return boolean overlap
     * */
    public static boolean appointmentValidation(LocalDateTime start, LocalDateTime end, int customerId){
        //Checking for Time overlaps  new is the appointment to be added...old is the appointment to be checked against.
        ObservableList<Appointment> tempList = AppointmentAccess.getAllAppointmentByCustomerId(customerId);
        LocalDateTime newStart = start;
        LocalDateTime newEnd = end;
        boolean overlap = false;
        for(int i = 0; i < tempList.size(); i++){
            LocalDateTime oldStart = tempList.get(i).getStart();
            LocalDateTime oldEnd = tempList.get(i).getEnd();
            //case 1
            if ((newStart.isAfter(oldStart) || newStart.isEqual(oldStart)) && (newStart.isBefore(oldEnd))){
                //case 1 error
                Helper.alertError("This appointment overlaps with another appointment. Please select a different time.");
                overlap = true;
                break;
            }
            //case 2
            else if ((newEnd.isAfter(oldStart)) && ((newEnd.isBefore(oldEnd)) || newEnd.isEqual(oldEnd))){
                //case 2 error
                Helper.alertError("This appointment overlaps with another appointment. Please select a different time.");
                overlap = true;
                break;
            }
            //case 3
            else if ((newStart.isBefore(oldStart) || newStart.isEqual(oldStart)) && (newEnd.isAfter(oldEnd) || newEnd.isEqual(oldEnd))){
                //case 3 error
                Helper.alertError("This appointment overlaps with another appointment. Please select a different time.");
                overlap = true;
                break;
            }
            //case that the start time and end time are the same
            else if (newStart.isEqual(newEnd)){
                Helper.alertError("The start time and end time cannot be the same. Please select different times.");
                overlap = true;
                break;
            }
            //case that end times/dates are before start times/dates
            else if (end.isBefore(start) || end.isEqual(start)){
                Helper.alertError("The end date/time is before the start date/time. Please select different date/times");
                overlap = true;
                break;
            }
            else{
                //no appointment overlap
                overlap = false;
            }
        }

        return overlap;
    }



}
