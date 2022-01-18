package easv.dk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override

    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUI/View/mainWindow.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Which movie do you wanna watch today?");
        stage.setScene(scene);
        stage.show();
        Alert alert = new Alert(Alert.AlertType.WARNING, "Remember to delete movies that have a personal rating under 6 and have not been opened in more than 2 years", ButtonType.OK);
        alert.showAndWait();
    }
}