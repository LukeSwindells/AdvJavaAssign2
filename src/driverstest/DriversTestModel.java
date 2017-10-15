package driverstest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Contains the data for the Drivers Test
 * @author Luke Swindells
 */
public class DriversTestModel {
    static int bgR = 255;
    static int bgG = 255;
    static int bgB = 255;
    static double bgA = 1.0;
    private static List<TestResult> testData = new ArrayList<TestResult>();
    TestResult t = new TestResult(0, 0, "Start", ZonedDateTime.now());
    static int carNo = 3;
    static String carColor = "Red";
    private static List<UserData> users = new ArrayList<UserData>();
    
    /**
     * Sets background colour. 
     */
    public static void setBackground(int r, int g, int b, double a){
        bgR = r;
        bgG = g;
        bgB = b;
        bgA = a;
    };
    
    /**
     * Get red from background colour. 
     */
    public static int getRed(){
        return bgR;
    }
    
    /**
     * Get blue from background colour. 
     */
    public static int getBlue(){
        return bgB;
    }
    
    /**
     * Get green from background colour. 
     */
    public static int getGreen(){
        return bgG;
    }
    
    /**
     * Get alpha from background colour. 
     */
    public static double getAlpha(){
        return bgA;
    }
    
    /**
     * Adds new result to table. 
     */
    public static void addResult(int aId, int aCarNo, String aResult, ZonedDateTime aTime){
        TestResult t = new TestResult(aId, aCarNo, aResult, aTime);
        testData.add(t);
    }
    
    public static int getCarNo()
    {
        return carNo;
    }
    
    public static void setCarNo(int i)
    {
        carNo = i;
    }
    
    public static String getCarColor()
    {
        return carColor;
    }
    
    public static void setCarColor(String s)
    {
        carColor = s;
    }
    
    /**
     * Get all data from table. 
     */
    public static List<TestResult> getTableData(){
        return testData;
    }
    
    public static void createUsers(){
        UserData a = new UserData("admin", "admin");
        users.add(a);
    }
    
    public static List<UserData> getUserData(){
        return users;
    }
}
