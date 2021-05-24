package PenManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void viewCollection(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("Scenes/ViewingCollection.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene =  new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void modifyCollection(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("Scenes/ModifyingCollection.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene =  new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void backToModifyCollectionMenu(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("Scenes/ModifyingCollection.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene =  new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void backToMainMenu(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("Scenes/MainMenu.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene =  new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void addToCollection(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("Scenes/AddToCollection.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene =  new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteFromCollection(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("Scenes/DeleteFromCollection.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene =  new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void editCollection(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("Scenes/EditCollection.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene =  new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
