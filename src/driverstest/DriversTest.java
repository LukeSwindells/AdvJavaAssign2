package driverstest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Launches the Program
 * @author Luke Swindells and Ravinder Kumar Sharma
 */
public class DriversTest extends Application {
    
    Stage aStage = new Stage();
    /**
     * Creates the stage and loads the terms and conditions.
     */
    @Override
    public void start(Stage primaryStage)throws Exception {
      DriversTestModel.createUsers();
      Stage aStage = new Stage();
      aStage = primaryStage; 
      Parent root = FXMLLoader.load(getClass().getResource("TermsConditions.fxml"));
      Scene scene = new Scene(root);
      aStage.setTitle("Drivers Test");
      aStage.setScene(scene);
      aStage.show(); 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
