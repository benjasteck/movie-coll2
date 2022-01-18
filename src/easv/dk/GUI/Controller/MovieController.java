package easv.dk.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;



import javafx.stage.Window;
import java.awt.*;
import java.io.File;

public class MovieController {


    @FXML
    private Button btnChooseFile;
    @FXML

    public Button btnCancelEditingMovie;

    @FXML
    private ComboBox comboBoxCategory;



   @FXML
    public void cancelEditingMovie(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Discard changes ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();


        if (alert.getResult() == ButtonType.YES) {
           Stage stage = (Stage) btnCancelEditingMovie.getScene().getWindow();
           stage.close();


        }

    }

    public void chooseCategory(ActionEvent actionEvent) {
    }

    @FXML
    public void initialize() {

        //adds different Categories to the comboBox that the user can choose from

        comboBoxCategory.getItems().removeAll(comboBoxCategory.getItems());
        comboBoxCategory.getItems().addAll("Action", "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Thriller", "Western");
        comboBoxCategory.getSelectionModel().select("Action");
    }

    public void chooseFile(ActionEvent actionEvent) {
        // double-clicking on the "choose" button will open another window with your computer´s files
        Window window = ((Node) (actionEvent.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);
        actionEvent.consume();
    }

}


