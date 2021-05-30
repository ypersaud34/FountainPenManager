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
 * The EditingController class contains some of the functionality relevant to record modification. The corresponding
 * FXML file is titled "EditCollection.fxml". The necessary GUI components are injected and then used to
 * construct a table from which the user can select a pen for modification. The pen is the sent to an intermediary
 * class titled Transfer which holds the pen so it can be accessed by the necessary window.
 */
public class EditingController extends SceneController implements Initializable {

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
     * Initializes the EditingController.
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
}