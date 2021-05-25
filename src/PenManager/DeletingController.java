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

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The DeletingController class contains functionality relevant to the 'Delete' feature window. The corresponding
 * FXML file is titled "DeleteFromCollection.fxml". The necessary GUI components are injected and then used to
 * construct a DELETE statement which is then executed on the required database.
 */
public class DeletingController implements Initializable {

    // The following correspond the relevant table and columns
    @FXML
    private TableView<FountainPen> penCollection;
    @FXML
    private TableColumn<FountainPen,Integer> penIDColumn;
    @FXML
    private TableColumn<FountainPen,String> modelNameColumn;
    @FXML
    private TableColumn<FountainPen,String> brandColumn;
    @FXML
    private TableColumn<FountainPen,String> colorColumn;
    @FXML
    private TableColumn<FountainPen, Double> priceColumn;
    @FXML
    private TableColumn<FountainPen,String> nibColumn;
    @FXML
    private TableColumn<FountainPen,String> fillingMechanismColumn;
    @FXML
    private TableColumn<FountainPen, Date> dateEnteredColumn;

    // This list will be used to populate the table.
    ObservableList<FountainPen> pensToDisplay = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        try {
            populateTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Removes a record from the database. An DELETE statement is constructed by calling buildDeleteStatement()
     * and then given to the DatabaseManager class for execution.
     * class for execution.
     * @throws SQLException if any SQL related errors occurs in executeStatement() or populateTable().
     * @throws ClassNotFoundException if the required class can not be located in the executeStatement() or
     * populateTable() methods.
     */
    public void delete() throws SQLException, ClassNotFoundException {
            DatabaseManager.executeStatement(buildDeleteStatement());
            emptyTable();
            populateTable();
    }
    /**
     * Populates the penCollection table. A SELECT query is executed and performed on the connected database.
     **/
    private void populateTable() throws SQLException, ClassNotFoundException {

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

                pensToDisplay.add(pen);

                DatabaseManager.close();
            }



        penIDColumn.setCellValueFactory(new PropertyValueFactory<>("PenID"));
        modelNameColumn.setCellValueFactory(new PropertyValueFactory<>("ModelName"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("Color"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        nibColumn.setCellValueFactory(new PropertyValueFactory<>("Nib"));
        fillingMechanismColumn.setCellValueFactory(new PropertyValueFactory<>("Mechanism"));
        dateEnteredColumn.setCellValueFactory(new PropertyValueFactory<>("DateEntered"));

        penCollection.setItems(pensToDisplay);
    }

    private void emptyTable(){
        penCollection.getItems().clear();
    }

    public void backToModifyCollectionMenu(ActionEvent click) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Scenes/ModifyingCollection.fxml"));
            Stage stage = (Stage) ((Node)click.getSource()).getScene().getWindow();
            Scene scene =  new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    private String buildDeleteStatement(){
        return "DELETE FROM pens WHERE pen_id=" + penCollection.getSelectionModel().getSelectedItem().getPenID();
    }
}