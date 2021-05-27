package PenManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The SceneController class contains most of the functionality used to switch between scenes/windows.
 */
public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Loads a window displaying the current pen collection.
     *
     * @param event Used to load the viewing table when the 'View Collection' button is clicked
     */
    public void changeToViewCollection(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("Scenes/ViewingCollection.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException i) {
            System.out.println("Error: View Collection Window Could Not Be Loaded.");
            i.printStackTrace();
        }
    }

    /**
     * Loads a window displaying several options to modify the current collection.
     *
     * @param event Used to load the modify menu when the 'Modify Collection' button is clicked
     */
    public void changeToModifyCollection(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("Scenes/ModifyingCollection.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException i) {
            System.out.println("Error: Modifying Menu Could Not Be Loaded.");
            i.printStackTrace();
        }
    }

    /**
     * Goes back the the main menu.
     *
     * @param event Used to load the main menu when the 'Back' button is clicked
     */
    public void backToMainMenu(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("Scenes/MainMenu.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException i) {
            System.out.println("Error: Main Menu Could Not Be Loaded.");
            i.printStackTrace();
        }
    }

    /**
     * Loads a window displaying several empty input fields each used for different pen specifications.
     *
     * @param event Used to load the adding window when the 'Add' button is clicked
     */
    public void changeToAdd(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("Scenes/AddToCollection.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException i) {
            System.out.println("Error: Adding Window Could Not Be Loaded.");
            i.printStackTrace();
        }
    }

    /**
     * Loads a window displaying the current collection from which users can select a pen to delete.
     *
     * @param event Used to load the deleting window when the 'Delete' button is clicked
     */
    public void changeToDelete(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("Scenes/DeleteFromCollection.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException i) {
            System.out.println("Error: Deleting Window Could Not Be Loaded.");
            i.printStackTrace();
        }
    }

    /**
     * Loads a window displaying the current collection from which users can select a pen to edit.
     *
     * @param event Used to load the editing window when the 'Edit' button is clicked
     */
    public void changeToEditingScreen(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("Scenes/EditCollection.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException i) {
            System.out.println("Error: Editing Menu Could Not Be Loaded.");
            i.printStackTrace();
        }
    }
}
