package driverstest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Contains the data for the Drivers Test
 * @author Luke Swindells and Ravinder Kumar Sharma
 */
public class DriversTestModel {
    private static int bgR = 255;
    private static int bgG = 255;
    private static int bgB = 255;
    private static double bgA = 1.0;
    private static List<TestResult> testData = new ArrayList<TestResult>();
    TestResult t = new TestResult(0, 0, "Start", ZonedDateTime.now());
    private static int carNo = 3;
    private static String carColor = "Green";
    private static List<UserData> users = new ArrayList<UserData>();
    private static String testType = "Speeding";
    private static int testTime = 30;
    
    /**
     * Sets background colour. 
     * @param r
     * @param g
     * @param b
     * @param a
     */
    public static void setBackground(int r, int g, int b, double a){
        bgR = r;
        bgG = g;
        bgB = b;
        bgA = a;
    };
    
    /**
     * Get red from background colour. 
     * @return int for the red value
     */
    public static int getRed(){
        return bgR;
    }
    
    /**
     * Get blue from background colour. 
     * @return int for the blue value
     */
    public static int getBlue(){
        return bgB;
    }
    
    /**
     * Get green from background colour. 
     * @return int for the green value
     */
    public static int getGreen(){
        return bgG;
    }
    
    /**
     * Get alpha from background colour. 
     * @return Double for the alpha value.
     */
    public static double getAlpha(){
        return bgA;
    }
    
    /**
     * Adds new result to table. 
     * @param aId Result ID
     * @param aCarNo Car Clicked
     * @param aResult String for Result "Success" "Misclick" or "Miss"
     * @param aTime Time Car Was Clicked
     */
    public static void addResult(int aId, int aCarNo, String aResult, ZonedDateTime aTime){
        TestResult t = new TestResult(aId, aCarNo, aResult, aTime);
        testData.add(t);
    }
    
    /**
     * Adds new result to table. 
     * @param aId Result ID
     * @param aCarNo Car Clicked
     * @param aResult String for Result "Success" "Misclick" or "Miss"
     * @param aTime Time Car Was Clicked
     * @param aRating Integer between 0 and 3
     */
    public static void addResult(int aId, int aCarNo, String aResult, ZonedDateTime aTime, int aRating) {
        TestResult t = new TestResult(aId, aCarNo, aResult, aTime, aRating);
        testData.add(t);
    }
    /**
     * 
     * @return number of cars
     */
    public static int getCarNo()
    {
        return carNo;
    }
    /**
     * Changes the number of cars
     * @param i car number.
     */
    public static void setCarNo(int i)
    {
        carNo = i;
    }
    /**
     * 
     * @return colour for the cars
     */
    public static String getCarColor()
    {
        return carColor;
    }
    /**
     * Changes the colour of cars
     * @param s "Red" "Green" or "Blue"
     */
    public static void setCarColor(String s)
    {
        carColor = s;
    }
    /**
     * 
     * @return "Speeding" or "Hazard"
     */
    public static String getTestType()
    {
        return testType;
    }
    /**
     * Changes test type
     * @param s "Speeding" or "Hazard"
     */
    public static void setTestType(String s)
    {
        testType = s;
    }
    /**
     * 
     * @return The length the test will run
     */
    public static int getTestTime()
    {
        return testTime;
    }
    /**
     * Changes the length of time the test will run
     * @param i Time in seconds
     */
    public static void setTestTime(int i)
    {
        testTime = i;
    }
    
    /**
     * Get all data for the table. 
     * @return The list of TestResult for the Table
     */
    public static List<TestResult> getTableData(){
        return testData;
    }
    
    /**
     * Creates Users and adds them to users
     */
    public static void createUsers(){
        UserData a = new UserData("admin", "admin");
        UserData b = new UserData("student", "student");
        users.add(a);
        users.add(b);
    }
    
    /**
     * Get all user data. 
     * @return The user data of usernames passwords and demos left.
     */
    public static List<UserData> getUserData(){
        return users;
    }
}
