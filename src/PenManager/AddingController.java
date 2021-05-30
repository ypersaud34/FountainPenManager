package PenManager;

import DBConnection.DatabaseManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * The AddingController class contains functionality relevant to the 'Add' feature window. The corresponding
 * FXML file is titled "AddToCollection.fxml". The necessary GUI components are injected and then used to
 * construct a INSERT statement which is then executed on the required database.
 */
public class AddingController extends SceneController implements Initializable {

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
    @FXML
    private Label prompt;

    /**
     * Initializes the AddingController.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateChoiceBoxes();
    }

    /**
     * Writes a record to the database. An INSERT statement is constructed by calling buildInsertStatement()
     * and then passed to the DatabaseManager class for execution.
     */
    public void add() {
        try {
            if (allDetailsEntered()) {
                DatabaseManager.executeStatement(buildInsertStatement());
                DatabaseManager.close();
                prompt.setText("Pen Added!");
            } else {
                prompt.setText("All Details Required!");
            }
        } catch (IllegalArgumentException e) {
            prompt.setText("Invalid Price!");
        } catch (SQLException s) {
            System.out.println("Error: Could Not Generate INSERT statement.");
        }
    }

    /**
     * Builds and returns an INSERT statement using the injected fields. The return value is meant to be used to add
     * a record to the database.
     *
     * @return an executable INSERT statement.
     * @throws SQLException if any SQL related errors occurs when attempting to generate an new penID.
     */
    private String buildInsertStatement() throws SQLException {

        // Assign the following using the injected fields.
        int id = FountainPen.getNewPenID();
        String nameInput = nameField.getText();
        String brandInput = brandField.getText();
        String colorInput = colorField.getText();
        double priceInput = Double.parseDouble(priceField.getText());
        String nibInput = nibOptions.getValue();
        String mechanismInput = fillingMechanismOptions.getValue();
        LocalDate entryDate = LocalDate.now();

        if (Double.isNaN(priceInput) || priceInput < 0) {

            throw new IllegalArgumentException();

        } else {

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
                    + entryDate + "')";

        }
    }

    /**
     * Populates the nibOptions and fillingMechanismOptions ChoiceBoxes. The Nib class provides the options for
     * the nibOptions while the FillingMechanism class provides the options for the fillingMechanismOptions.
     */
    private void populateChoiceBoxes() {
        nibOptions.getItems().addAll(Nib.getNibTypes());
        fillingMechanismOptions.getItems().addAll(FillingMechanisms.getMechanismTypes());
    }

    /**
     * Returns true if there are no empty input fields, false otherwise.
     *
     * @return a boolean indicating whether or not all fields have been filled.
     */
    private boolean allDetailsEntered() {
        return !nameField.getText().isEmpty() &&
                !brandField.getText().isEmpty() &&
                !colorField.getText().isEmpty() &&
                !priceField.getText().isEmpty() &&
                !nibOptions.getValue().isEmpty() &&
                !fillingMechanismOptions.getValue().isEmpty();
    }
}
