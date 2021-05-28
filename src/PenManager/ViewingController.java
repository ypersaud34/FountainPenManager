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
 * The ViewingController class contains functionality relevant to the 'View Collection' feature window. The corresponding
 * FXML file is titled "ViewCollection.fxml". The necessary GUI components are injected and then used to
 * construct a table displaying all current pen records.
 */
public class ViewingController implements Initializable {

    // The following correspond the relevant table and columns
    @FXML
    private TableView<FountainPen> table;
    @FXML
    private TableColumn<FountainPen, Integer> penID;
    @FXML
    private TableColumn<FountainPen, String> modelName;
    @FXML
    private TableColumn<FountainPen, String> brand;
    @FXML
    private TableColumn<FountainPen, String> color;
    @FXML
    private TableColumn<FountainPen, Double> price;
    @FXML
    private TableColumn<FountainPen, String> nib;
    @FXML
    private TableColumn<FountainPen, String> fillingMechanism;
    @FXML
    private TableColumn<FountainPen, Date> dateEntered;

    ObservableList<FountainPen> collection = FXCollections.observableArrayList();


    /**
     * Initializes the ViewingController.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            populateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Populates the penCollection table. A SELECT * query is executed and performed on the connected database.
     *
     * @throws SQLException if there is any database related problem.
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
            collection.add(pen);
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

        DatabaseManager.close();
    }

    /**
     * Goes back the the main menu.
     *
     * @param event Used to load the main menu when the 'Back' button is clicked
     */
    public void backToMainMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scenes/MainMenu.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException i) {
            System.out.println("Error: Main Menu Could Not Be Loaded.");
            i.printStackTrace();
        }
    }
}