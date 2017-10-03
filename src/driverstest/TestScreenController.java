package driverstest;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class for the Test Screen
 *
 * @author Luke Swindells
 */
public class TestScreenController implements Initializable {
    
    @FXML private Rectangle background;
    @FXML private ImageView car1;
    @FXML private ImageView car2;
    @FXML private ImageView car3;
    @FXML private ImageView hazard;
    @FXML private Button startButton;
    private int activeCar = 0;
    private long hazardStartTime;
    private Boolean car1Move = true;
    private Boolean car2Move = true;
    private Boolean car3Move = true;
    private int resultID = 0;
    
    
    /**
     * Runs test when start button is pressed.
     */
    public void startButtonPressed(ActionEvent event)throws Exception {
        startButton.setVisible(false);
        runTest();
    }
    
    /**
     * When car 1 is click check if car is active and adds result to model based on result
     */
    public void car1Clicked(){
        if(activeCar == 1){
            car1Move = false;
            DriversTestModel.addResult(resultID++, 1, "Success", ZonedDateTime.now());
        }
        else{
            DriversTestModel.addResult(resultID++ , 1, "Misclick", ZonedDateTime.now());
        }
    }
    /**
     * When car 2 is click check if car is active and adds result to model based on result
     */
    public void car2Clicked(){
        if(activeCar == 2){
            car2Move = false;
            DriversTestModel.addResult(resultID++, 2, "Success", ZonedDateTime.now());
        }
        else{
            DriversTestModel.addResult(resultID++, 2, "Misclick", ZonedDateTime.now());
        }
    }
    /**
     * When car 3 is click check if car is active and adds result to model based on result
     */
    public void car3Clicked(){
        if(activeCar == 3){
            car3Move = false;
            DriversTestModel.addResult(resultID++, 3, "Success", ZonedDateTime.now());
        }
        else{
            DriversTestModel.addResult(resultID++, 3, "Misclick", ZonedDateTime.now());
        }
    }
    /**
     * Checks if car number i can move and if so moves it.
     */
    private void moveCar(int i)
    {
        ImageView toMove;
        switch(i){
            case 1:toMove = car1;
                   break;
            case 2:toMove = car2;
                   break;
            case 3:toMove = car3;
                   break;
            default:toMove = car1;
        }
        if(!(car1.getLayoutX()- toMove.getLayoutX() < 75 && car1.getLayoutX() - toMove.getLayoutX() > 0)){
                if(!(car2.getLayoutX() - toMove.getLayoutX() < 75 && car2.getLayoutX() - toMove.getLayoutX() > 0)){
                    if(!(car3.getLayoutX() - toMove.getLayoutX() < 75 && car3.getLayoutX() - toMove.getLayoutX() > 0)){
                        switch(i){
                            case 1:if(car1Move){toMove.setLayoutX(toMove.getLayoutX()+1);}
                                   break;
                            case 2:if(car2Move){toMove.setLayoutX(toMove.getLayoutX()+1);}
                                   break;
                            case 3:if(car3Move){toMove.setLayoutX(toMove.getLayoutX()+1);}
                                   break;
                        }
                    }
                }
            }  
        if(toMove.getLayoutX() >= 935){
            toMove.setLayoutX(0);
        }
    };
    /**
     * Generates hazard in front of car if able.
     */
    public void createHazard(){
        if(activeCar == 0){
            if(car1.getLayoutX()< 920 && (car2.getLayoutX() - car1.getLayoutX() > 180 || car2.getLayoutX() - car1.getLayoutX() < 0) && (car3.getLayoutX() - car1.getLayoutX() > 180 || car3.getLayoutX() - car1.getLayoutX() < 0)){
                activeCar = 1;
                hazard.setLayoutX(car1.getLayoutX()+180);
                hazardStartTime = System.currentTimeMillis();
                hazard.setVisible(true);
            }
            else if(car1.getLayoutX()< 920 && (car1.getLayoutX() - car2.getLayoutX() > 180 || car1.getLayoutX() - car2.getLayoutX() < 0) && (car3.getLayoutX() - car2.getLayoutX() > 180 || car3.getLayoutX() - car2.getLayoutX() < 0)){
                activeCar = 2;
                hazard.setLayoutX(car2.getLayoutX()+180);
                hazardStartTime = System.currentTimeMillis();
                hazard.setVisible(true);
            }
            else if(car1.getLayoutX()< 920 && (car1.getLayoutX() - car3.getLayoutX() > 180 || car1.getLayoutX() - car3.getLayoutX() < 0) && (car2.getLayoutX() - car3.getLayoutX() > 180 || car2.getLayoutX() - car3.getLayoutX() < 0)){
                activeCar = 3;
                hazard.setLayoutX(car3.getLayoutX()+180);
                hazardStartTime = System.currentTimeMillis();
                hazard.setVisible(true);
            }
        }
        else {
            if(hazardStartTime + 3000 < System.currentTimeMillis()){
                if(car1Move & car2Move & car3Move){
                    DriversTestModel.addResult(resultID++, activeCar, "Miss", ZonedDateTime.now());
                }
                activeCar = 0;
                hazard.setVisible(false);
                car1Move = true;
                car2Move = true;
                car3Move = true;
            }
        }
    }
    /**
     * Ends test and opens result table.
     */
    public void endTest() throws Exception
    {
        Stage aStage = (Stage)startButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ResultTable.fxml"));
        Scene scene = new Scene(root);
        aStage.setTitle("Results");
        aStage.setScene(scene);
    }
    /**
     * Runs test for 5 minutes then calls endtest.
     */
    public void runTest()throws Exception{
        Long end = System.currentTimeMillis() + 30000;
        AnimationTimer a = new AnimationTimer(){@Override public void handle(long now){
            moveCar(1);
            moveCar(2);
            moveCar(3);
            createHazard();
            if(System.currentTimeMillis() > end){
                try {
                    endTest();
                } catch (Exception ex) {
                    Logger.getLogger(TestScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }};
        a.start();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int r = DriversTestModel.getRed();
        int g = DriversTestModel.getGreen();
        int b = DriversTestModel.getBlue();
        double a = DriversTestModel.getAlpha();
        TestResult t = new TestResult(0, 0, "Start", ZonedDateTime.now());
        background.setFill(Color.rgb(r, g, b, a));
        hazard.setVisible(false);
    }    

    private Date ZonedDateTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
