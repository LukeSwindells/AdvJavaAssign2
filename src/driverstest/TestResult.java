package driverstest;

import java.time.ZonedDateTime;

/**
 * Stores the data for a single click during the test
 * @author Luke Swindells
 */
public class TestResult {
    private final int id;
    private final int carNo;
    private final String result;
    private final ZonedDateTime time;
    
    public TestResult(int aId, int aCarNo, String aResult, ZonedDateTime aTime){
        id = aId;
        carNo = aCarNo;
        result = aResult;
        time = aTime;
    }
    
    /**
     * Return Result ID. 
     */
    public int getID(){
        return id; 
    }
    /**
     * Return car ID. 
     */
    public int getCarNo(){
        return carNo; 
    }
    /**
     * Return Result String. 
     */
    public String getResult(){
        return result; 
    }
    /**
     * Return Result Time. 
     */
    public ZonedDateTime getTime(){
        return time; 
    }
    
    
}
