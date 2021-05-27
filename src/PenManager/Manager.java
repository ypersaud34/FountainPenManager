package PenManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Launches the main application and displays the main menu.
 */
public class Manager extends Application {
    /**
     * Runs the application.
     *
     * @param args - arguments passed to method.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Loads the main menu.
     *
     * @param stage - the stage that contains the application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scenes/MainMenu.fxml"));
        Scene mainMenu = new Scene(root);
        stage.setScene(mainMenu);
        //mainMenu.getStylesheets().add(getClass().getResource("Theme.css").toExternalForm());
        stage.show();
    }
}

