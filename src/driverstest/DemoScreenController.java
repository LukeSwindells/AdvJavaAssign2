    package driverstest;

import java.net.MalformedURLException;
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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class for the Demo Screen
 *
 * @author Luke Swindells and Ravinder Kumar Sharma
 */
public class DemoScreenController implements Initializable {
    
    @FXML private Rectangle background;
    @FXML private ImageView hazard;
    @FXML private Button startButton;
    @FXML private AnchorPane pane;
    private int activeCar = 0;
    private long hazardStartTime;
    private Boolean clickCorrect = false;
    private Boolean hazardActive = false;
    private Boolean speedingActive = false;
    private int speedingCar = 0;
    private int resultID = 0;
    private List<ImageView> cars = new ArrayList<ImageView>();
    private List<Boolean> carsMove = new ArrayList<Boolean>();
    private Image carImage;
    private Image pinkImage;
    
    
    
    /**
     * Runs test when start button is pressed.
     */
    @FXML
    public void startButtonPressed(ActionEvent event)throws Exception {
        startButton.setVisible(false);
        runTest();
    }
    
    /**
     * When a car is active stop the car after one second.
     */
    public void autoClicked(){
        if(activeCar > 0){
            if(hazardStartTime + 1000 < System.currentTimeMillis()){
                carsMove.set(activeCar - 1, false);
                if(!clickCorrect){
                    DriversTestModel.addResult(resultID++, activeCar - 1, "Success", ZonedDateTime.now());
                }
                clickCorrect = true;
            }
        }
    }

    /**
     * Checks if each car can move and if so moves it.
     */
    private void moveCars()
    {
        ImageView toMove;
        Boolean canMove = true; 
        for(int i=0; i < cars.size(); i++ )
        {
            canMove = true;
            toMove = cars.get(i);
            for(int j=0; j < cars.size(); j++){
                if(i != j){    
                    if(cars.get(j).getLayoutX()- toMove.getLayoutX() < 75 && cars.get(j).getLayoutX() - toMove.getLayoutX() > 0){
                        canMove = false;
                    }
                }
            }
            if(canMove && carsMove.get(i)){
                if(speedingActive && i == speedingCar){
                    toMove.setLayoutX(toMove.getLayoutX()+1.25);
                }
                else{toMove.setLayoutX(toMove.getLayoutX()+1);}
            }
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
                if(!(cars.get(i).getLayoutX()< 920)){
                    if(!carsMove.get(i)){
                        hazardCheck = false;
                    }
                }
            if(hazardCheck){
                activeCar = i+1;
                hazard.setLayoutY(-10);
                hazard.setLayoutX(cars.get(i).getLayoutX() + 210);
                hazardStartTime = System.currentTimeMillis();
                hazard.setVisible(true);
                hazardActive = true;
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
                for(int i = 0; i < carsMove.size(); i++){
                    carsMove.set(i, true);
                }
                activeCar = 0;
                hazard.setVisible(false);
                hazardActive = false;
            }
        }
        if(hazardActive){
            hazard.setLayoutY(hazard.getLayoutY() + 1);
        }
    }
    
    /**
     * Causes a car to start speeding and changes its colour to pink if able.
     */
    private void carSpeeding(){
        Boolean speedingCheck = true;
        if(activeCar == 0){
            Random r = new Random();
            int i = r.nextInt(DriversTestModel.getCarNo());
                if(!(cars.get(i).getLayoutX()< 920)){
                    if(!carsMove.get(i)){
                        speedingCheck = false;
                    }
                }
            if(speedingCheck){
                hazardStartTime = System.currentTimeMillis();
                speedingCar = i;
                activeCar = i+1;
                cars.get(i).setImage(pinkImage);
                speedingActive = true;
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
                for(int i = 0; i < carsMove.size(); i++){
                    carsMove.set(i, true);
                }
                activeCar = 0;
                speedingActive = false;
                cars.get(speedingCar).setImage(carImage);
            }
        }
    }
    /**
     * Ends test and opens result table.
     */
    public void endTest() throws Exception
    {
        Stage aStage = (Stage)startButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("TermsConditions.fxml"));
        Scene scene = new Scene(root);
        aStage.setTitle("Drivers Test");
        aStage.setScene(scene);
    }
    /**
     * Runs test for the set time then calls endtest.
     */
    public void runTest()throws Exception{
        Long end = System.currentTimeMillis() + DriversTestModel.getTestTime() * 1000;
        AnimationTimer a = new AnimationTimer(){@Override public void handle(long now){
            moveCars();
            if(DriversTestModel.getTestType() == "Hazard"){
                createHazard();
            }
            if(DriversTestModel.getTestType() == "Speeding"){
                carSpeeding();
            }
            autoClicked();
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
     * Initializes the controller class and generates the cars.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switch(DriversTestModel.getCarColor()){
                case "Red": carImage = new Image(getClass().getResourceAsStream("/Car2.png"));
                    break;
                case "Green": carImage = new Image(getClass().getResourceAsStream("/Car1.png"));
                    break;
                case "Blue": carImage = new Image(getClass().getResourceAsStream("/Car3.png"));
                    break;
                default: carImage = new Image(getClass().getResourceAsStream("/Car2.png"));
            }  
        pinkImage = new Image(getClass().getResourceAsStream("/CarP.png"));
        for(int i = 0; i < DriversTestModel.getCarNo(); i++)
        {
            ImageView car = new ImageView(carImage);
            car.setLayoutX(i * 150);
            car.setLayoutY(169);
            car.setVisible(true);
            car.toFront();
            pane.getChildren().add(car);
            cars.add(i, car);
            carsMove.add(true);
        }
        background.setVisible(false);
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
