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
    private ComboBox<?> carNumBox;
    @FXML
    private ComboBox<?> carColBox;
    @FXML
    private Label demoLeftField;
    
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
    private void checkLogin(ActionEvent event) {
        boolean usernameVaild = false;
        boolean passwordVaild = false;
        int userID = 0;
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
        }
        else{
            demoLeftField.setText("Invalid Username or Password");
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startButton.setVisible(false);
        DriversTestModel.createUsers();
    }    

    
    
}