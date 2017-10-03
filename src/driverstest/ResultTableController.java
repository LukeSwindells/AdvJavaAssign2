package driverstest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class for Result Table
 *
 * @author Luke Swindells
 */
public class ResultTableController implements Initializable {
    @FXML TableView table;
    @FXML Button printButton;
    private static List<TestResult> testData;

    /**
     * When Print button is pressed save table to Table.txt. 
     */
    public void printButtonPressed(ActionEvent event)throws Exception {
        File f = new File("Table.txt");
        if(!f.exists()){
            f.createNewFile();
        }
        testData = DriversTestModel.getTableData();
        BufferedWriter w = new BufferedWriter(new FileWriter(f));
        for(TestResult test : testData){
            String s = "ID:" + test.getID() + " CarID:" + test.getCarNo() + " Result:" + test.getResult() + " Time:" + test.getTime() + System.getProperty("line.separator");
            w.write(s);
        }
        w.flush();
        w.close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.setEditable(true);
        TableColumn idCol = new TableColumn("ID");
        TableColumn carCol = new TableColumn("Car ID");
        TableColumn resultCol = new TableColumn("Result");
        TableColumn timeCol = new TableColumn("Time");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        carCol.setCellValueFactory(new PropertyValueFactory<>("CarNo"));
        resultCol.setCellValueFactory(new PropertyValueFactory<>("Result"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
        table.getColumns().addAll(idCol, carCol,resultCol,timeCol);
        testData = DriversTestModel.getTableData();
        for(int i = 0; i < testData.size(); i++){
            table.getItems().add(testData.get(i));
        }
        table.setEditable(false);
    }    
}
