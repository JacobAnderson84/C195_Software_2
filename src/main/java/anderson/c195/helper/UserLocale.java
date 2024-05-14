package anderson.c195.helper;

import java.time.ZoneId;
/** This is class for the users locale information.
 * The method here are for setting and getting the users locale based on their system.
 * */
public class UserLocale {
    private static java.util.Locale locale;
    private static ZoneId zoneId = ZoneId.systemDefault();

    /** This is the setter for locale.
     * */
    public static void setLocale(){
        locale = java.util.Locale.getDefault();
    }
    /** This is the getter for locale.
     * @return locale
     * */
    public static java.util.Locale getLocale(){
        return locale;
    }
    /** This is the getter for zoneId.
     * @return zoneId
     * */
    public static ZoneId getZoneId() {
        return zoneId;
    }
    /** This is the setter for zoneId.
     * */
    public static void setZoneId(ZoneId zoneId) {
        UserLocale.zoneId = zoneId;
    }
}
