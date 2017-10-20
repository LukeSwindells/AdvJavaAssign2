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
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class for colour selector
 *
 * @author Luke Swindells and Ravinder Kumar Sharma
 */
public class BgColourSelectController implements Initializable {
    private int r = 255;
    private int g = 255;
    private int b = 255;
    private double a = 1.0;
    
    @FXML private Rectangle colorPreview; 
    @FXML private TextField rText; 
    @FXML private TextField gText; 
    @FXML private TextField bText; 
    @FXML private TextField aText;
    @FXML private Slider rSlide;
    @FXML private Slider gSlide;
    @FXML private Slider bSlide;
    @FXML private Slider aSlide;
    @FXML private Button doneButton;
    
    /**
     * When Done button is pressed return to terms and conditions. 
     */
    public void doneButtonPressed(ActionEvent event)throws Exception{
        Node source = (Node) event.getSource();
        Stage aStage = (Stage)source.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("TermsConditions.fxml"));
        Scene scene = new Scene(root);
        aStage.setTitle("Drivers Test");
        aStage.setScene(scene);
    }
    
    /**
     * Initializes the controller class and launches listers to update colour when
     * sliders are moved.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rText.textProperty().bind(rSlide.valueProperty().asString("%.0f"));
        gText.textProperty().bind(gSlide.valueProperty().asString("%.0f"));
        bText.textProperty().bind(bSlide.valueProperty().asString("%.0f"));
        aText.textProperty().bind(aSlide.valueProperty().asString("%.0f"));
        rSlide.valueProperty().addListener((observableValue, oldValue, newValue) ->
        {
            r = newValue.intValue();
            colorPreview.setFill(Color.rgb(r, g, b, a));
            DriversTestModel.setBackground(r, g, b, a);
        });
        gSlide.valueProperty().addListener((observableValue, oldValue, newValue) ->
        {
            g = newValue.intValue();
            colorPreview.setFill(Color.rgb(r, g, b, a));
            DriversTestModel.setBackground(r, g, b, a);
        });
        bSlide.valueProperty().addListener((observableValue, oldValue, newValue) ->
        {
            b = newValue.intValue();
            colorPreview.setFill(Color.rgb(r, g, b, a));
            DriversTestModel.setBackground(r, g, b, a);
        });
        aSlide.valueProperty().addListener((observableValue, oldValue, newValue) ->
        {
            a = newValue.doubleValue();
            colorPreview.setFill(Color.rgb(r, g, b, a));
            DriversTestModel.setBackground(r, g, b, a);
        });
    }        
}
