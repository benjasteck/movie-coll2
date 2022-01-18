package easv.dk.GUI.Controller;

import easv.dk.BE.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EditMovieController {
    public TextField txt_title;
    public TextField txt_ImbdRating;
    public TextField txt_userRating;
    public ComboBox comboBoxCategory;
    public Button btnSaveMovie;
    public Button btnCancelEditingMovie;
    public Button btnChooseFile;
    public TextField txt_movieUrl;

    public void setInfo(Movie selectedItem) {
        txt_title.setText(selectedItem.getTitle());
        txt_ImbdRating.setText(selectedItem.getTitle());
        txt_userRating.setText(selectedItem.getTitle());

    }

    public void saveNewMovie(ActionEvent actionEvent) {
    }

    public void cancelEditingMovie(ActionEvent actionEvent) {
    }
    
    @FXML
    public void initialize() {

        //adds different Categories to the comboBox that the user can choose from

        comboBoxCategory.getItems().removeAll(comboBoxCategory.getItems());
        comboBoxCategory.getItems().addAll("Action", "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Thriller", "Western");
        comboBoxCategory.getSelectionModel().select("Action");
    }

    public void chooseFile(ActionEvent actionEvent) {
    }
}
