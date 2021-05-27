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

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The ChangingDetailsController class contains the rest of the functionality relevant to record modification. The corresponding
 * FXML file is titled "EditInputs.fxml". The necessary GUI components are injected and given default values using a
 * FountainPen object retrieved from the Transfer class. The user can edit the inputs and write the newly modified record
 * to the database.
 */
public class ChangingDetailsController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField colorField;
    @FXML
    private TextField priceField;
    @FXML
    private ChoiceBox<String> nibField;
    @FXML
    private ChoiceBox<String> fillingMechanismField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nibField.getItems().addAll(Nib.getNibTypes());
        fillingMechanismField.getItems().addAll(FillingMechanisms.getMechanismTypes());
        populateFields(Transfer.getDataToTransfer());
    }
    /**
     * Modifies a record in the database. An UPDATE statement is constructed by calling buildUpdateStatement()
     * and then passed to the DatabaseManager class for execution.
     */
    public void editEntry() {
        try {
            DatabaseManager.executeStatement(buildUpdateStatement());
        } catch (SQLException s) {
            System.out.println("Error: Could Not Modify Record");
            s.printStackTrace();
        }
    }
    /**
     * Populates the TextFields and ChoiceBoxes using a selected FountainPen object.
     * @param penToEdit - a FountainPen object selected from the editing screen.
     */
    private void populateFields(FountainPen penToEdit) {

        nameField.setText(penToEdit.getModelName());
        brandField.setText(penToEdit.getBrand());
        colorField.setText(penToEdit.getColor());
        priceField.setText(Double.toString(penToEdit.getPrice()));
        nibField.setValue(penToEdit.getNib());
        fillingMechanismField.setValue(penToEdit.getMechanism());

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
        String nibChange = nibField.getValue();
        String mechanismChange = fillingMechanismField.getValue();
        DatabaseManager.getConnection().close();
        System.out.println(Transfer.getDataToTransfer().getPenID());

        return "UPDATE pens " +
                "SET " +
                "model_name = " + "'" + nameChange + "'" + "," +
                "brand = " + "'" + brandChange + "'" + ", " +
                "color = " + "'" + colorChange + "'" + ", " +
                "price = " + priceChange + ", " +
                "nib = " + "'" + nibChange + "'" + ", " +
                "filling_mechanism = " + "'" + mechanismChange + "'" +
                " WHERE pen_id = " + Transfer.getDataToTransfer().getPenID();
    }
    /**
     * Loads a window displaying the current collection from which users can select a pen to edit.
     *
     * @param event Used to load the editing window when the 'Edit' button is clicked
     */
    public void changeToEditingScreen(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scenes/EditCollection.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
