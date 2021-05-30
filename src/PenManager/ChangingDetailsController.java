package PenManager;

import DBConnection.DatabaseManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The ChangingDetailsController class contains the rest of the functionality relevant to record modification. The corresponding
 * FXML file is titled "EditInputs.fxml". The necessary GUI components are injected and given default values using a
 * FountainPen object retrieved from the Transfer class. The user can edit the inputs and write the newly modified record
 * to the database.
 */
public class ChangingDetailsController extends SceneController implements Initializable {

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
     * Initializes the ChangingDetailsController.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nibOptions.getItems().addAll(Nib.getNibTypes());
        fillingMechanismOptions.getItems().addAll(FillingMechanisms.getMechanismTypes());
        populateFields(TableManager.getDataToTransfer());
    }

    /**
     * Modifies a record in the database. An UPDATE statement is constructed by calling buildUpdateStatement()
     * and then passed to the DatabaseManager class for execution.
     */
    public void editEntry() {
        try {
            if (allDetailsEntered()) {
                DatabaseManager.executeStatement(buildUpdateStatement());
                prompt.setText("Pen Updated!");
                DatabaseManager.close();
            } else {
                prompt.setText("All Details Required!");
            }
        } catch (SQLException s) {
            System.out.println("Error: Could Not Modify Record");
            s.printStackTrace();
        } catch (IllegalArgumentException e) {
            prompt.setText("Invalid Price!");
        }
    }

    /**
     * Populates the TextFields and ChoiceBoxes using a selected FountainPen object.
     *
     * @param penToEdit - a FountainPen object selected from the editing screen.
     */
    private void populateFields(FountainPen penToEdit) {

        nameField.setText(penToEdit.getModelName());
        brandField.setText(penToEdit.getBrand());
        colorField.setText(penToEdit.getColor());
        priceField.setText(Double.toString(penToEdit.getPrice()));
        nibOptions.setValue(penToEdit.getNib());
        fillingMechanismOptions.setValue(penToEdit.getMechanism());

    }

    /**
     * Builds and returns an UPDATE statement using the injected fields. The return value is meant to be used to modify
     * a record in the database.
     *
     * @return an executable INSERT statement.
     * @throws SQLException if any SQL related errors occurs when attempting to close the database.
     */
    private String buildUpdateStatement() throws SQLException {

        String nameChange = nameField.getText();
        String brandChange = brandField.getText();
        String colorChange = colorField.getText();
        double priceChange = Double.parseDouble(priceField.getText());
        String nibChange = nibOptions.getValue();
        String mechanismChange = fillingMechanismOptions.getValue();
        DatabaseManager.getConnection().close();

        if (Double.isNaN(priceChange) || priceChange < 0) {
            throw new IllegalArgumentException();
        } else {
            return "UPDATE pens " +
                    "SET " +
                    "model_name = " + "'" + nameChange + "'" + "," +
                    "brand = " + "'" + brandChange + "'" + ", " +
                    "color = " + "'" + colorChange + "'" + ", " +
                    "price = " + priceChange + ", " +
                    "nib = " + "'" + nibChange + "'" + ", " +
                    "filling_mechanism = " + "'" + mechanismChange + "'" +
                    " WHERE pen_id = " + TableManager.getDataToTransfer().getPenID();
        }
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
