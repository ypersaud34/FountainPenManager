package PenManager;

import DBConnection.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * The AddingController class contains functionality relevant to the 'Add' feature window. The corresponding
 * FXML file is titled "AddToCollection.fxml". The necessary GUI components are injected and then used to
 * construct a INSERT statement which is then executed on the required database.
 */
public class AddingController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField colorField;
    @FXML
    private TextField priceField;
    @FXML
    private ChoiceBox<String> nibOptions;
    @FXML
    private ChoiceBox<String> fillingMechanismOptions;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateChoiceBoxes();
    }

    /**
     * Writes a record to the database. An INSERT statement is constructed by calling buildInsertStatement()
     * and then passed to the DatabaseManager class for execution.
     * @throws SQLException if any SQL related errors occurs in the DatabaseManager methods or buildInsertStatement()
     * method.
     * @throws ClassNotFoundException if the required class can not be located in the getConnection() method used
     * in the executeStatement() method.
     */
    public void add() throws SQLException, ClassNotFoundException {

        DatabaseManager.executeStatement(buildInsertStatement());
        DatabaseManager.close();

    }

    /**
     * Reloads the modifying menu from the 'Add' scene. This method is assigned to the 'Back' button and called
     * when the button is clicked.
     * @param event - used to advance to the next scene when the 'Back' button is clicked.
     * @throws IOException if there is an I/0 error when loading the 'Modifying' menu.
     */
    public void backToModifyCollectionMenu(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Scenes/ModifyingCollection.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene =  new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    /**
     * Builds and returns an INSERT statement using the injected fields. The return value is meant to be used as
     * an executable SQL statement.
     * @return an executable INSERT statement.
     * @throws SQLException if any SQL related errors occurs when attempting to generate an new penID.
     */
    private String buildInsertStatement() throws SQLException{

        // Assign the following using the injected fields.
        int id = FountainPen.getNewPenID();
        String nameInput = nameField.getText();
        String brandInput = brandField.getText();
        String colorInput = colorField.getText();
        double priceInput = Double.parseDouble(priceField.getText());
        String nibInput = nibOptions.getValue();
        String mechanismInput = fillingMechanismOptions.getValue();
        LocalDate entryDate = LocalDate.now();

        return "INSERT INTO pens " +
                "(pen_id, model_name, brand, color, price, nib, filling_mechanism, date_entered) "
                + "VALUES("
                + id + ",'"
                + nameInput + "','"
                + brandInput + "','"
                + colorInput + "','"
                + priceInput + "','"
                + nibInput + "','"
                + mechanismInput + "','"
                + entryDate +"')";
    }
    /**
     * Populates the nibOptions and fillingMechanismOptions ChoiceBoxes. The Nib class provides the options for
     * the nibOptions while the FillingMechanism class provides the options for the fillingMechanismOptions.
     *
     */
    private void populateChoiceBoxes(){
        nibOptions.getItems().addAll(Nib.getNibTypes());
        fillingMechanismOptions.getItems().addAll(FillingMechanisms.getMechanismTypes());
    }
}
