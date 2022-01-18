package easv.dk.GUI.Controller;

import easv.dk.BE.Movie;
import easv.dk.DAL.MovieDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;




import javafx.stage.Window;
import java.awt.*;
import java.io.File;
import java.util.Calendar;

public class MovieController {
private Movie movie;

    @FXML
    private Button btnChooseFile;
    @FXML

    public Button btnCancelEditingMovie;

    @FXML
    private ComboBox comboBoxCategory;

    @FXML
    private Button btnSaveMovie;

    @FXML
    private TextField txt_title;

    @FXML
    private TextField txt_ImbdRating;

    @FXML
    private TextField txt_userRating;

    @FXML
    private TextField txt_movieUrl;

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
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Movie Files", "*.mp4", "*.mpeg4"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            txt_movieUrl.setText(file.getPath());

        } else  {
            System.out.println("error"); // or something else
        }

    }


    @FXML
    private void saveNewMovie(ActionEvent actionEvent) throws Exception {
        String title = txt_title.getText();
        Double imdbRating = Double.parseDouble(txt_ImbdRating.getText());
        Double userRating = Double.parseDouble(txt_userRating.getText());
        String lastView = movie.getLastView();
        String movieUrl = txt_movieUrl.getText();

        Movie movieCreated = new Movie(title, userRating, imdbRating, lastView, movieUrl, 0);
        MovieDAO.createMovie(movieCreated);
        Stage stage = (Stage) btnSaveMovie.getScene().getWindow();
        stage.close();
    }
}




