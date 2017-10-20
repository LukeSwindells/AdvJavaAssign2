package driverstest;

import java.time.ZonedDateTime;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Stores the data for a single click during the test
 * @author Luke Swindells and Ravinder Kumar Sharma
 */
public class TestResult {
    private final int id;
    private final int carNo;
    private final String result;
    private final ZonedDateTime time;
    private final ImageView rating;
    
    /**
     * Creates new result with no rating. 
     * @param aId Result ID
     * @param aCarNo Car Clicked
     * @param aResult String for Result "Success" "Misclick" or "Miss"
     * @param aTime Time Car Was Clicked
     */
    public TestResult(int aId, int aCarNo, String aResult, ZonedDateTime aTime){
        Image zeroStar = new Image("/0star.png");
        id = aId;
        carNo = aCarNo;
        result = aResult;
        time = aTime;
        rating = new ImageView(zeroStar);
    }
    
    /**
     * Creates new result.
     * @param aId Result ID
     * @param aCarNo Car Clicked
     * @param aResult String for Result "Success" "Misclick" or "Miss"
     * @param aTime Time Car Was Clicked
     * @param aRating Integer between 0 and 3
     */
    public TestResult(int aId, int aCarNo, String aResult, ZonedDateTime aTime, int aRating){
        Image zeroStar = new Image("/0star.png");
        Image oneStar = new Image("/1star.png");
        Image twoStar = new Image("/2star.png");
        Image threeStar = new Image("/3star.png");
        id = aId;
        carNo = aCarNo;
        result = aResult;
        time = aTime;
        switch(aRating){
            case 1:rating = new ImageView(oneStar);
                    break;
            case 2:rating = new ImageView(twoStar);
                    break;
            case 3:rating = new ImageView(threeStar);
                    break;
            default:rating = new ImageView(zeroStar);
        }
    }
    
    /**
     * @return Result ID. 
     */
    public int getID(){
        return id; 
    }
    /**
     * @return car ID. 
     */
    public int getCarNo(){
        return carNo; 
    }
    /**
     * @return Result String. 
     */
    public String getResult(){
        return result; 
    }
    /**
     * @return Result Time. 
     */
    public ZonedDateTime getTime(){
        return time; 
    }
    /**
     * @return Rating Image. 
     */
    public ImageView getRating(){
        return rating; 
    }
    
    
    
}
