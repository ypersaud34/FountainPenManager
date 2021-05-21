package PenManager;

import DBConnection.DatabaseManager;
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
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeletingController implements Initializable {

    // The following correspond the relevant table and columns
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

    // This list will be used to populate the table.
    ObservableList<FountainPen> collection = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();
    }
    // Allows the user to go back to the previous menu.
    public void backToModifyCollectionMenu(ActionEvent click){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scenes/ModifyingCollection.fxml"));
            Stage stage = (Stage) ((Node)click.getSource()).getScene().getWindow();
            Scene scene =  new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void delete() throws SQLException {
        //
        FountainPen penToDelete = table.getSelectionModel().getSelectedItem();
        String deleteStatement = "DELETE FROM pens WHERE pen_id=" + penToDelete.getPenID();
        DatabaseManager.getConnection().createStatement().execute(deleteStatement);
        emptyTable();
        populateTable();
    }
    private void populateTable(){
        try {

            ResultSet pens = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * FROM pens");

            while (pens.next()){
                FountainPen pen = new FountainPen(
                        pens.getInt("pen_id"),
                        pens.getString("model_name"),
                        pens.getString("brand"),
                        pens.getString("color"),
                        pens.getDouble("price"),
                        pens.getString("nib"),
                        pens.getString("filling_mechanism"),
                        pens.getDate("date_entered").toLocalDate());
                collection.add(pen);
            }

        }
        catch (SQLException e){
            Logger.getLogger(DeletingController.class.getName()).log(Level.SEVERE,null, e);
            System.out.println("Status: Failed");
        }
        penID.setCellValueFactory(new PropertyValueFactory<>("PenID"));
        modelName.setCellValueFactory(new PropertyValueFactory<>("ModelName"));
        brand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        color.setCellValueFactory(new PropertyValueFactory<>("Color"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        nib.setCellValueFactory(new PropertyValueFactory<>("Nib"));
        fillingMechanism.setCellValueFactory(new PropertyValueFactory<>("Mechanism"));
        dateEntered.setCellValueFactory(new PropertyValueFactory<>("DateEntered"));

        table.setItems(collection);
    }
    private void emptyTable(){
        table.getItems().clear();
    }
}