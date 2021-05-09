package PenManager;

import DBConnection.FPDBConnection;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.transform.Result;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private TableView<FountainPen> table;
    @FXML
    private TableColumn<FountainPen,String> model_name;
    @FXML
    private TableColumn<FountainPen,String> brand;
    @FXML
    private TableColumn<FountainPen,String> color;
    @FXML
    private TableColumn<FountainPen, Double> price;
    @FXML
    private TableColumn<FountainPen,Nib> nib ;
    @FXML
    private TableColumn<FountainPen,FillingMechanism> filling_mechanism;
    @FXML
    private TableColumn<FountainPen, Date> date_entered;

    ObservableList<FountainPen> collection = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection FPDB = FPDBConnection.getConnection();

            ResultSet resultSet = FPDB.createStatement().executeQuery("SELECT * FROM pens");

            while (resultSet.next()){
                collection.add(new FountainPen(resultSet.getString("model_name"),
                        resultSet.getString("brand"),
                        resultSet.getString("color"),
                        resultSet.getDouble("price"),
                        resultSet.getObject("nib"),
                        resultSet.getObject("filling_Mechanism"))
            }

            model_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
            brand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
            color.setCellValueFactory(new PropertyValueFactory<>("Color"));
            price.setCellValueFactory(new PropertyValueFactory<>("Price"));
            nib.setCellValueFactory(new PropertyValueFactory<>("Nib"));
            filling_mechanism.setCellValueFactory(new PropertyValueFactory<>("Fill Mechanism"));
            date_entered.setCellValueFactory(new PropertyValueFactory<>("Entered"));
        }
        catch (Exception e){
            e.printStackTrace();
        }







    }
}
