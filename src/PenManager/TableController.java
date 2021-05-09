package PenManager;

import DBConnection.FPDBConnection;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private TableColumn<FountainPen,String> nib ;
    @FXML
    private TableColumn<FountainPen,String> filling_mechanism;
    @FXML
    private TableColumn<FountainPen, Date> date_entered;

    ObservableList<FountainPen> collection = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection FPDB = FPDBConnection.getConnection();

            ResultSet resultSet = FPDB.createStatement().executeQuery("SELECT * FROM pens;");

            while (resultSet.next()){
                collection.add(new FountainPen(
                        resultSet.getString("model_name"),
                        resultSet.getString("brand"),
                        resultSet.getString("color"),
                        resultSet.getString("nib"),
                        resultSet.getString("filling_Mechanism"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("date_entered")));

            }

            model_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
            brand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
            color.setCellValueFactory(new PropertyValueFactory<>("Color"));
            price.setCellValueFactory(new PropertyValueFactory<>("Price"));
            nib.setCellValueFactory(new PropertyValueFactory<>("Nib"));
            filling_mechanism.setCellValueFactory(new PropertyValueFactory<>("Fill Mechanism"));
            date_entered.setCellValueFactory(new PropertyValueFactory<>("Entered"));

            table.setItems(collection);
        }
        catch (SQLException e){
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE,null, e);
        }
    }
    public void backToMainMenu(ActionEvent click) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scenes/MainMenu.fxml"));
            Stage stage = (Stage) ((Node)click.getSource()).getScene().getWindow();
            Scene scene =  new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
