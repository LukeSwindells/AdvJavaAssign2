package driverstest;

import java.time.ZonedDateTime;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Stores the data for a single click during the test
 * @author Luke Swindells
 */
public class TestResult {
    private final int id;
    private final int carNo;
    private final String result;
    private final ZonedDateTime time;
    private final ImageView rating;
    
    public TestResult(int aId, int aCarNo, String aResult, ZonedDateTime aTime){
        Image zeroStar = new Image("/0star.png");
        id = aId;
        carNo = aCarNo;
        result = aResult;
        time = aTime;
        rating = new ImageView(zeroStar);
    }
    
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
    
    public ImageView getRating(){
        return rating; 
    }
    
    
    
}
