package PenManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The ViewingController class contains functionality relevant to the 'View Collection' feature window. The corresponding
 * FXML file is titled "ViewCollection.fxml". The necessary GUI components are injected and then used to
 * construct a table displaying all current pen records.
 *
 */
public class ViewingController extends SceneController implements Initializable {

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

    /**
     * Initializes the ViewingController.
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
        } catch (SQLException e) {
            System.out.println("Error: Table Could Not Be Loaded.");
            e.printStackTrace();
        }
    }
}