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
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import java.util.Random;

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
    private Boolean clickCorrect = false;
    private int resultID = 0;
    private List<ImageView> cars = new ArrayList<ImageView>();
    private List<Boolean> carsMove = new ArrayList<Boolean>();
    
    
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
    public void carClicked(int i){
        if(activeCar == i + 1){
            carsMove.set(i, false);
            DriversTestModel.addResult(resultID++, i+1, "Success", ZonedDateTime.now());
            clickCorrect = true;
        }
        else{
            DriversTestModel.addResult(resultID++ , i+1, "Misclick", ZonedDateTime.now());
        }
    }

    /**
     * Checks if car number i can move and if so moves it.
     */
    private void moveCars()
    {
        ImageView toMove;
        Boolean canMove = true; 
        for(int i=0; i < cars.size(); i++ )
        {
            toMove = cars.get(i);
            for(int j=0; j < cars.size(); j++){
                if(i != j){    
                    if(cars.get(j).getLayoutX()- toMove.getLayoutX() < 75 && cars.get(j).getLayoutX() - toMove.getLayoutX() > 0){
                        canMove = false;
                    }
                }
            }
            if(canMove && carsMove.get(i)){toMove.setLayoutX(toMove.getLayoutX()+1);}
            if(toMove.getLayoutX() >= 935){
            toMove.setLayoutX(0);    
            }
        }    
    }
    /**
     * Generates hazard in front of car if able.
     */
    public void createHazard(){
        Boolean hazardCheck = true;
        if(activeCar == 0){
            Random r = new Random();
            int i = r.nextInt(DriversTestModel.getCarNo());
            for(int j=0; j < cars.size(); j++){
                if(i != j){
                    if(!(cars.get(i).getLayoutX()< 920 && (cars.get(j).getLayoutX() - cars.get(i).getLayoutX() > 180 || cars.get(j).getLayoutX() - cars.get(i).getLayoutX() < 0))){
                        hazardCheck = false;
                    }
                }
            }
            if(hazardCheck){
                activeCar = i+1;
                hazard.setLayoutX(car1.getLayoutX()+180);
                hazardStartTime = System.currentTimeMillis();
                hazard.setVisible(true);
            }
        }
        else {
            if(hazardStartTime + 3000 < System.currentTimeMillis()){
                if(!clickCorrect){
                    DriversTestModel.addResult(resultID++, activeCar, "Miss", ZonedDateTime.now());
                }
                else{
                    clickCorrect = false;
                }
                activeCar = 0;
                hazard.setVisible(false);
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
        Long end = System.currentTimeMillis() + 300000;
        AnimationTimer a = new AnimationTimer(){@Override public void handle(long now){
            moveCars();
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
        Image image = new Image("../Car1.png");
        for(int i = 0; i < DriversTestModel.getCarNo(); i++)
        {
            switch(DriversTestModel.getCarColor()){
                case "Red": image = new Image("../Car2.png");
                    break;
                case "Green": image = new Image("../Car1.png");
                    break;
                case "Blue": image = new Image("../Car3.png");
                    break;
                default: image = new Image("../Car2.png");       
            }  
            ImageView car = new ImageView();
            final int finali = i;
            car.setOnMouseClicked(e -> { 
               carClicked(finali); 
            });
            car.setImage(image);
            car.setLayoutX(i * 100);
            cars.add(car);
            carsMove.add(true);
        }
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
