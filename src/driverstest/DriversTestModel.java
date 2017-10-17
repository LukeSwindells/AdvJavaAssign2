package driverstest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Contains the data for the Drivers Test
 * @author Luke Swindells
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
    
    public static void addResult(int aId, int aCarNo, String aResult, ZonedDateTime aTime, int aRating) {
        TestResult t = new TestResult(aId, aCarNo, aResult, aTime, aRating);
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
    
    public static String getTestType()
    {
        return testType;
    }
    
    public static void setTestType(String s)
    {
        testType = s;
    }
    
    public static int getTestTime()
    {
        return testTime;
    }
    
    public static void setTestTime(int i)
    {
        testTime = i;
    }
    
    /**
     * Get all data from table. 
     */
    public static List<TestResult> getTableData(){
        return testData;
    }
    
    public static void createUsers(){
        UserData a = new UserData("admin", "admin");
        UserData b = new UserData("student", "student");
        users.add(a);
        users.add(b);
    }
    
    public static List<UserData> getUserData(){
        return users;
    }
}
