package PenManager;

import DBConnection.FPDBConnection;
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
    private TableColumn<FountainPen,Integer> penID;
    @FXML
    private TableColumn<FountainPen,String> modelName;
    @FXML
    private TableColumn<FountainPen,String> brand;
    @FXML
    private TableColumn<FountainPen,String> color;
    @FXML
    private TableColumn<FountainPen, Double> price;
    @FXML
    private TableColumn<FountainPen,String> nib;
    @FXML
    private TableColumn<FountainPen,String> fillingMechanism;
    @FXML
    private TableColumn<FountainPen, Date> dateEntered;

    ObservableList<FountainPen> collection = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection = FPDBConnection.getConnection();

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM pens");

            while (resultSet.next()){
                FountainPen pen = new FountainPen(
                        resultSet.getInt("pen_id"),
                        resultSet.getString("model_name"),
                        resultSet.getString("brand"),
                        resultSet.getString("color"),
                        resultSet.getDouble("price"),
                        resultSet.getString("nib"),
                        resultSet.getString("filling_Mechanism"),
                        resultSet.getDate("date_entered"));
                collection.add(pen);
            }

        }
        catch (SQLException e){
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE,null, e);
            System.out.println("Status: Failed");
        }
        penID.setCellValueFactory(new PropertyValueFactory<>("Pen Number"));
        modelName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        brand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        color.setCellValueFactory(new PropertyValueFactory<>("Color"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        nib.setCellValueFactory(new PropertyValueFactory<>("Nib"));
        fillingMechanism.setCellValueFactory(new PropertyValueFactory<>("Fill Mechanism"));
        dateEntered.setCellValueFactory(new PropertyValueFactory<>("Entered"));

        table.setItems(collection);
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
