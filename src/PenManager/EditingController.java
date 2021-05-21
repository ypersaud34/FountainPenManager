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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditingController implements Initializable {

    // The following correspond the relevant table and columns
    @FXML
    private TableView<FountainPen> table;
    @FXML
    private TableColumn<FountainPen,Integer> penID;
    @FXML
    private TableColumn<FountainPen,String> modelName;
    @FXML
    private TableColumn<FountainPen,String> brand;
    @FXML
    private TableColumn<FountainPen,String> color;
    @FXML
    private TableColumn<FountainPen, Double> price;
    @FXML
    private TableColumn<FountainPen,String> nib;
    @FXML
    private TableColumn<FountainPen,String> fillingMechanism;
    @FXML
    private TableColumn<FountainPen, Date> dateEntered;

    ObservableList<FountainPen> collection = FXCollections.observableArrayList();

    //The following correspond the editing text fields
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
    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();
    }
    public void backToModifyCollectionMenu(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scenes/ModifyingCollection.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene =  new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void changeToEditingScreen(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scenes/EditInputs.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene =  new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void editEntry(){
        FountainPen penToEdit = table.getSelectionModel().getSelectedItem();
    }
    private void populateTable(){
        try {
            //Display the entire table
            ResultSet pens = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * FROM pens");

            // Add each pen from DB to collection
            while (pens.next()){
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

        }
        catch (SQLException e){
            Logger.getLogger(EditingController.class.getName()).log(Level.SEVERE,null, e);
            System.out.println("Status: Failed");
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
    }
    private void populateTextFields(){
        FountainPen penToEdit = table.getSelectionModel().getSelectedItem();
        nameField.setText(penToEdit.getModelName());
        brandField.setText(penToEdit.getBrand());
        colorField.setText(penToEdit.getColor());
        priceField.setText(Double.toString(penToEdit.getPrice()));
        nibField.setValue(penToEdit.getNib());
        fillingMechanismField.setValue(penToEdit.getMechanism());

    }
}