package anderson.c195.model;

/** This is the class for User.
 * This contains the getters and setters for a User object */
public class User {
    private int userId;
    private String userName;
    private String password;
    /** This is a constructor for User.
     * Constructor for a user object. */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
    /** This is the getter for userId.
     * @return userId
     * */
    public int getUserId() {
        return userId;
    }
    /** This is the setter for userId.
     * @param userId The userId to set
     * */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /** This is the getter for userName.
     * @return userName
     * */
    public String getUserName() {
        return userName;
    }
    /** This is the setter for userName.
     * @param userName The userName to set
     * */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /** This is the getter for password.
     * @return password
     * */
    public String getPassword() {
        return password;
    }
    /** This is the setter for password.
     * @param password The password to set
     * */
    public void setPassword(String password) {
        this.password = password;
    }
    /** This method returns the userId and userName as a String.
     * This method takes the userId and userName and turns it into a readable string for combo boxes.
     * @return Returns (userId + " " + userName)
     * */
    @Override
    public String toString() {
        return(userId + " " + userName);
    }
}
