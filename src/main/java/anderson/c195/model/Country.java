package anderson.c195.model;

/** This is the class for Country.
 * This contains methods for getting and setting Country objects
 * */
public class Country {
    private int id;
    private String name;

    /** This is the constructor for Country.
     * Constructor for a Country object
     * */
    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }
    /** This is the getter for id.
     * @return id
     * */
    public int getId() {
        return id;
    }
    /** This is the getter for Name.
     * @return name
     * */
    public String getName() {
        return name;
    }
    /** This method return the name as a String.
     * When interacting with the database the data will be not be readable. This method return the information in a readable format. 
     * @return name
     * */
    @Override
    public String toString() {
        return(name);
    }
}
