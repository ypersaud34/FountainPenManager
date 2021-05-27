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
import javafx.scene.control.Label;
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
    private TableColumn<FountainPen, Integer> penIDColumn;
    @FXML
    private TableColumn<FountainPen, String> modelNameColumn;
    @FXML
    private TableColumn<FountainPen, String> brandColumn;
    @FXML
    private TableColumn<FountainPen, String> colorColumn;
    @FXML
    private TableColumn<FountainPen, Double> priceColumn;
    @FXML
    private TableColumn<FountainPen, String> nibColumn;
    @FXML
    private TableColumn<FountainPen, String> fillingMechanismColumn;
    @FXML
    private TableColumn<FountainPen, Date> dateEnteredColumn;
    @FXML
    private Label prompt;

    // This list will be used to populate the table.
    ObservableList<FountainPen> pensToDisplay = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            populateTable();
        } catch (SQLException s) {
            System.out.println("Error: Table Could Not Be Loaded.");
            s.printStackTrace();
        }
    }

    /**
     * Removes a record from the database. An DELETE statement is constructed by calling buildDeleteStatement()
     * and then given to the DatabaseManager class for execution.
     * class for execution.
     */
    public void delete(){
        try {
            DatabaseManager.executeStatement(buildDeleteStatement());
            emptyTable();
            populateTable();
            prompt.setText("Pen Deleted!");
        } catch (SQLException s) {
            System.out.println("Error: Table Could Not Be Reloaded.");
            s.printStackTrace();
        }
    }

    /**
     * Populates the penCollection table. A SELECT * query is executed and performed on the connected database.
     * @throws SQLException if there is a problem with the database.
     **/
    private void populateTable() throws SQLException {
        ResultSet pens = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * FROM pens");

        while (pens.next()) {

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

    /**
     * Removes all elements from the TableView.
     */
    private void emptyTable() {
        penCollection.getItems().clear();
    }

    /**
     * Reloads the modifying menu from the 'Add' scene. This method is assigned to the 'Back' button and called
     * when the button is clicked.
     *
     * @param event - used to advance to the next scene when the 'Back' button is clicked.
     */
    public void backToModifyCollectionMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scenes/ModifyingCollection.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException i) {
            System.out.println("Error: Modifying Menu Could Not Be Loaded.");
            i.printStackTrace();
        }
    }

    /**
     * Builds and returns an DELETE statement using the injected fields. The return value is meant to remove a record
     * from the database.
     *
     * @return an executable DELETE statement.
     */
    private String buildDeleteStatement() {
        return "DELETE FROM pens WHERE pen_id=" + penCollection.getSelectionModel().getSelectedItem().getPenID();
    }
}