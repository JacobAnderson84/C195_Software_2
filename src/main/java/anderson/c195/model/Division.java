package anderson.c195.model;

/** This is the class for Division.
 * This contains the getters and setters for a Division object */
public class Division {
    private int id;
    private String name;
    private int countryId;
    /** This is a constructor for Division.
     * Constructor for a division object. */
    public Division(int id, String name, int countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }
    /** This is the getter for id.
     * @return id
     * */
    public int getId() {
        return id;
    }
    /** This is the setter for id.
     * @param id The id to set
     * */
    public void setId(int id) {
        this.id = id;
    }
    /** This is the getter for name.
     * @return name
     * */
    public String getName() {
        return name;
    }
    /** This is the setter for name.
     * @param name The name to set
     * */
    public void setName(String name) {
        this.name = name;
    }
    /** This is the getter for countryId.
     * @return countryId
     * */
    public int getCountryId() {
        return countryId;
    }
    /** This is the setter for countryId.
     * @param countryId The countryId to set
     * */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    /** This method returns the name as a String.
     * This method takes the divisionName and turns it into a readable string for combo boxes.
     * @return name
     * */
    @Override
    public String toString() {
        return(name);
    }
}
