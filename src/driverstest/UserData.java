package driverstest;

/**
 * Stores the data for a user
 * @author Luke Swindells and Ravinder Kumar Sharma
 */
public class UserData {
    String username = new String();
    String password = new String();
    int demoLeft = 3;
    
    /**
     * Creates a new user
     * @param u Username
     * @param p Password
     */
    public UserData (String u, String p){
        username = u;
        password = p;
    }
    /**
     * 
     * @return Username
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * Changes the username
     * @param s username
     */
    public void setUsername(String s)
    {
        username = s;
    }
    
    /**
     * 
     * @return Password
     */
    public String getPassword()
    {
        return password;
    }
    
    /**
     * Changes the password
     * @param s 
     */
    public void setPassword(String s)
    {
        password = s;
    }
    
    /**
     * 
     * @return The amount of demos remaining 
     */
    public int getDemoLeft()
    {
        return demoLeft;
    }
    /**
     * Changes the amount of Demos left.
     * @param i 
     */
    public void setDemoLeft(int i)
    {
        demoLeft = i;
    }
    
    /**
     * Decrements the amount of demos left.
     */
    public void useDemo(){
        demoLeft--;
    }

}
