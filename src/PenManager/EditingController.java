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
 * The EditingController class contains some of the functionality relevant to record modification. The corresponding
 * FXML file is titled "EditCollection.fxml". The necessary GUI components are injected and then used to
 * construct a table from which the user can select a pen for modification. The pen is the sent to an intermediary
 * class titled Transfer which holds the pen so it can be accessed by the necessary window.
 */
public class EditingController implements Initializable {

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
     * Populates the penCollection table. A SELECT * query is executed and performed on the connected database.
     **/
    private void populateTable() throws SQLException {
        //Display the entire table
        ResultSet pens = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * FROM pens");

        // Add each pen from DB to collection
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

        //Set the columns to the proper values
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
     * Loads the editing form from the 'Edit' scene. This method is assigned to the 'Edit' button and called
     * when the button is clicked.
     *
     * @param event - used to advance to the next scene when the 'Edit' button is clicked.
     */
    public void changeToEditingScreen(ActionEvent event) {

        FountainPen penToEdit = table.getSelectionModel().getSelectedItem();
        Transfer.setDataToTransfer(penToEdit);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scenes/EditInputs.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setUserData(penToEdit);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException i) {
            System.out.println("Error: Editing Screen Could Not Be Loaded.");
            i.printStackTrace();
        }
    }

    /**
     * Reloads the modifying menu from the 'Edit' scene. This method is assigned to the 'Back' button and called
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

}