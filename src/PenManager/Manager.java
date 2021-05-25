package PenManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Used to launch to main program and
 * */
public class Manager extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Scenes/MainMenu.fxml"));
            Scene mainMenu = new Scene(root);
            stage.setScene(mainMenu);
            //mainMenu.getStylesheets().add(getClass().getResource("Theme.css").toExternalForm());
            stage.show();
    }
}

