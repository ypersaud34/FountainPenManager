package PenManager;

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

public class AddingController {
    private String nameInput;
    private String brandInput;
    private String colorInput;
    @FXML
    private TextField name;
    @FXML
    private TextField brand;
    @FXML
    private TextField color;
    @FXML
    private ChoiceBox<String> nib;
    @FXML
    private ChoiceBox<String> fillingMechanism;
    @FXML
    private Button addEntry;



    public void add(ActionEvent addEntry){
        nameInput=name.getText();
        System.out.println(nameInput);
        brandInput=brand.getText();
        System.out.println(brandInput);
        colorInput=color.getText();
        System.out.println(colorInput);
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
