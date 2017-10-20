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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class for Result Table
 *
 * @author Luke Swindells and Ravinder Kumar Sharma
 */
public class ResultTableController implements Initializable {
    @FXML TableView table;
    @FXML Button printButton;
    private static List<TestResult> testData;
    @FXML
    private PieChart pieChart;

    /**
     * When Print button is pressed save table to Table.txt. 
     */
    @FXML
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
     * Initializes the controller class fills table and graph.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int noSuccess = 0;
        int noMisses = 0;
        int noMisclick = 0;
        table.setEditable(true);
        TableColumn idCol = new TableColumn("ID");
        TableColumn carCol = new TableColumn("Car ID");
        TableColumn resultCol = new TableColumn("Result");
        TableColumn timeCol = new TableColumn("Time");
        TableColumn starCol = new TableColumn("Rating");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        carCol.setCellValueFactory(new PropertyValueFactory<>("CarNo"));
        resultCol.setCellValueFactory(new PropertyValueFactory<>("Result"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
        starCol.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        table.getColumns().addAll(idCol, carCol,resultCol,timeCol,starCol);
        testData = DriversTestModel.getTableData();
        for(int i = 0; i < testData.size(); i++){
            table.getItems().add(testData.get(i));
            switch(testData.get(i).getResult()){
                case "Success": noSuccess++;
                                break;
                case "Miss": noMisses++;
                                break;   
                case "Misclick": noMisclick++;
                                break;
            }
        }
        table.setEditable(false);
        PieChart.Data success = new PieChart.Data("Success", noSuccess);
        PieChart.Data miss = new PieChart.Data("Misses", noMisses);
        PieChart.Data misclick = new PieChart.Data("Misclicks", noMisclick);
        pieChart.getData().add(miss);
        pieChart.getData().add(misclick);
        pieChart.getData().add(success);
    }    
}
