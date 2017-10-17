package driverstest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class for terms and conditions
 *
 * @author Luke Swindells
 */
public class TermsConditionsController implements Initializable {

    @FXML
    private Button startButton;
    @FXML
    private Button bgButton;
    @FXML
    private TextField userNameField;
    @FXML
    private Button startDemoButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> carNumBox;
    @FXML
    private ComboBox<String> carColBox;
    @FXML
    private Label demoLeftField;
    @FXML
    private ComboBox<String> testBox;
    private boolean loggedIn = false;
    private int userID = 0;
    @FXML
    private TextField testTimeField;
    
    /**
     * When Start button is pressed start test. 
     */
    @FXML
    public void startButtonPressed(ActionEvent event)throws Exception {
        Node source = (Node) event.getSource();
        Stage aStage = (Stage)source.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("TestScreen.fxml"));
        Scene scene = new Scene(root);
        aStage.setTitle("Driver's Test");
        aStage.setScene(scene);
    }
    
    /**
     * When Background button is pressed open colour selector. 
     */
    @FXML
    public void bgButtonPressed(ActionEvent event)throws Exception {
        Node source = (Node) event.getSource();
        Stage aStage = (Stage)source.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("BgColourSelect.fxml"));
        Scene scene = new Scene(root);
        aStage.setTitle("Change Colour");
        aStage.setScene(scene);
    }
    
    @FXML
    private void numberSelected(ActionEvent event) {
        DriversTestModel.setCarNo(Integer.parseInt(carNumBox.getValue()));
    }
    
    @FXML
    private void colorSelected(ActionEvent event) {
        DriversTestModel.setCarColor(carColBox.getValue());
    }
    
    @FXML
    private void testSelected(ActionEvent event) {
       DriversTestModel.setTestType(testBox.getValue()); 
    }
    
    @FXML
    private void changeTime(ActionEvent event) {
        DriversTestModel.setTestTime(Integer.parseInt(testTimeField.getText()));
    }
    
    @FXML
    private void demoPressed(ActionEvent event) throws Exception {
        if(loggedIn){
            if(DriversTestModel.getUserData().get(userID).getDemoLeft()>0){
                DriversTestModel.getUserData().get(userID).useDemo();
                Node source = (Node) event.getSource();
                Stage aStage = (Stage)source.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("DemoScreen.fxml"));
                Scene scene = new Scene(root);
                aStage.setTitle("Driver's Test Demo");
                aStage.setScene(scene);
            }
        }
    }
    
    @FXML
    private void checkLogin(ActionEvent event) {
        boolean usernameVaild = false;
        boolean passwordVaild = false;
        for(int i = 0; i < DriversTestModel.getUserData().size(); i++){
            if (userNameField.getText().equals(DriversTestModel.getUserData().get(i).getUsername())){
                usernameVaild = true;
                userID = i;
            }
        }
        if(usernameVaild){
            if (passwordField.getText().equals(DriversTestModel.getUserData().get(userID).getPassword())){
                passwordVaild = true;
            }
        }
        if(usernameVaild && passwordVaild){
            startButton.setVisible(true);
            demoLeftField.setText("Number of Demos Left: " + DriversTestModel.getUserData().get(userID).getDemoLeft());
            loggedIn = true;
        }
        else{
            demoLeftField.setText("Invalid Username or Password");
            loggedIn = true;
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startButton.setVisible(false);
        carNumBox.getItems().addAll("1","2","3","4","5");
        carColBox.getItems().addAll("Red","Blue","Green");
        testBox.getItems().addAll("Speeding", "Hazard");
    }    

    

    

   
}