package anderson.c195.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;
/** This class contains methods use for helping with logic involving Time.
 * The methods here are for time validation checks when adding and updating appointments. Ot formats the time combo boxes so that values selected fall with in the business hours of the company.
 * */
public class TimeHelper {
    private static ZonedDateTime timeZone;

    private static ObservableList<LocalTime> localTimeStart = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> localTimeEnd = FXCollections.observableArrayList();
    /** This method is for formatting the Time combo boxes.
     * When adding or updating an appointment this method manipulates the time values so that the combo box only shows hours that are with in the company business hours.
     * */
    protected static void comboBoxTimeFormatter(){
        ZonedDateTime startEST = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8,0),ZoneId.of("America/New_York"));
        ZonedDateTime endEST = ZonedDateTime.of(LocalDate.now(), LocalTime.of(22,0),ZoneId.of("America/New_York"));
        ZonedDateTime start = startEST.withZoneSameInstant(ZoneId.systemDefault());
        ZonedDateTime end = endEST.withZoneSameInstant(ZoneId.systemDefault());


        while(start.isBefore(end)){
            localTimeStart.add(start.toLocalTime());
            start = start.plusMinutes(30);
            localTimeEnd.add(start.toLocalTime());
        }
    }
    /** This method calls gets the LocalTimeStart.
     * This class checks the size of localTimeStart to see if it has any values. If its 0 then it calls another method to format the values.
     * @return localTimeStart
     * */
    public static ObservableList<LocalTime> getLocalTimeStart(){
        if(localTimeStart.size() == 0){
            comboBoxTimeFormatter();
        }
        return localTimeStart;
    }
    /** This method calls gets the LocalTimeEnd.
     * This class checks the size of localTimeEnd to see if it has any values. If its 0 then it calls another method to format the values.
     * @return localTimeEnd
     * */
    public static ObservableList<LocalTime> getLocalTimeEnd(){
        if(localTimeEnd.size() == 0){
            comboBoxTimeFormatter();
        }
        return localTimeEnd;
    }


}
