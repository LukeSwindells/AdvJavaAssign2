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
    
    /**
     * When Start button is pressed start test. 
     */
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
    public void bgButtonPressed(ActionEvent event)throws Exception {
        Node source = (Node) event.getSource();
        Stage aStage = (Stage)source.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("BgColourSelect.fxml"));
        Scene scene = new Scene(root);
        aStage.setTitle("Change Colour");
        aStage.setScene(scene);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}