package PenManager;

import DBConnection.DatabaseManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The DeletingController class contains functionality relevant to the 'Delete' feature window. The corresponding
 * FXML file is titled "DeleteFromCollection.fxml". The necessary GUI components are injected and then used to
 * construct a DELETE statement which is then executed on the required database.
 */
public class DeletingController extends SceneController implements Initializable {

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

    /**
     * Initializes the DeletingController.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            TableManager.populateTable(penCollection,
                    penIDColumn,
                    modelNameColumn,
                    brandColumn,
                    colorColumn,
                    priceColumn,
                    nibColumn,
                    fillingMechanismColumn,
                    dateEnteredColumn);
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
    public void delete() {
        try {
            DatabaseManager.executeStatement(buildDeleteStatement());
            TableManager.emptyTable();
            TableManager.populateTable(penCollection,
                    penIDColumn,
                    modelNameColumn,
                    brandColumn,
                    colorColumn,
                    priceColumn,
                    nibColumn,
                    fillingMechanismColumn,
                    dateEnteredColumn);
            prompt.setText("Pen Deleted!");
        } catch (SQLException s) {
            System.out.println("Error: Table Could Not Be Reloaded.");
            s.printStackTrace();
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