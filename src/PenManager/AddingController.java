package PenManager;

import DBConnection.FPDBConnection;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddingController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField brand;
    @FXML
    private TextField color;
    @FXML
    private TextField price;
    @FXML
    private ChoiceBox<String> nib;
    @FXML
    private ChoiceBox<String> fillingMechanism;
    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Populate the ChoiceBoxes
        nib.getItems().addAll(Nib.getNibTypes());
        fillingMechanism.getItems().addAll(FillingMechanism.getMechanismTypes());
    }

    //Method to add a new entry to the database
    public void add() {
        try {
            //Establish the connection
            Connection connection = FPDBConnection.getConnection();

            //Set the  necessary values
            int id = FountainPen.getNewPenID();
            String nameInput = name.getText();
            String brandInput = brand.getText();
            String colorInput = color.getText();
            double priceInput = Double.parseDouble(price.getText());
            String nibInput = nib.getValue();
            String mechanismInput = fillingMechanism.getValue();
            LocalDate entryDate = LocalDate.now();

            //Build the string for the INSERT INTO statement
            String insertInto = "INSERT INTO pens " +
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

            //Execute the statement
            connection.createStatement().execute(insertInto);
            // After execution, a new entry should appear on the DB.
        }
        catch(SQLException e){
                System.out.println(e.getErrorCode());
        }
    }

    //Allow the user to go back and select another feature
    public void backToModifyCollectionMenu(ActionEvent click) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scenes/ModifyingCollection.fxml"));
            Stage stage = (Stage) ((Node)click.getSource()).getScene().getWindow();
            Scene scene =  new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
