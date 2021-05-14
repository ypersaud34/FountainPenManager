package PenManager;

import DBConnection.FPDBConnection;
import com.sun.javafx.scene.control.behavior.TextInputControlBehavior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

;import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

public class AddingController implements Initializable {

    private String nameInput;
    private String brandInput;
    private String colorInput;
    private double priceInput;
    private String nibInput;
    private String mechanismInput;
    private LocalDate entryDate;

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
    private Button addEntry;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nib.getItems().addAll(Nib.getNibTypes());
        fillingMechanism.getItems().addAll(FillingMechanism.getMechanismTypes());
    }
    public void add() {
        try {
            Connection connection = FPDBConnection.getConnection();
            String sq ="'";
            int id = FountainPen.getNumberOfPens()+1;
            nameInput = sq+name.getText()+sq;
            brandInput = sq+brand.getText()+sq;
            colorInput = sq+color.getText()+sq;
            priceInput = Double.parseDouble(price.getText());
            nibInput = sq+nib.getValue()+sq;
            mechanismInput = sq+fillingMechanism.getValue()+sq;
            entryDate=LocalDate.now();
            String insertInto = "INSERT INTO pens " +
                    "(pen_id, model_name, brand, color, price, nib, filling_mechanism, date_entered) VALUES("
                    + id + ","
                    + nameInput + ","
                    + brandInput + ","
                    + colorInput + ","
                    + priceInput + ","
                    + nibInput + ","s
                    + mechanismInput + ","
                    + sq + entryDate + sq +")";
            connection.createStatement().execute(insertInto);
        }
        catch(SQLException e){
            e.getErrorCode();
        }
    }
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
