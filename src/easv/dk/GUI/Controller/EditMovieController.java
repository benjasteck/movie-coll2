package easv.dk.GUI.Controller;

import easv.dk.BE.Movie;
import easv.dk.BLL.Manager;
import easv.dk.DAL.MovieDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class EditMovieController {
    public TextField txt_movieUrl;
    public Button btnChooseFile;
    @FXML
    private TextField txt_title;
    @FXML
    private TextField txt_ImbdRating;
    @FXML
    private TextField txt_userRating;
    @FXML
    private ComboBox comboBoxCategory;
    @FXML
    private Button btnSaveMovie;
    @FXML
    private Button btnCancelEditingMovie;
    private Movie passedMovie;

    Manager manager = new Manager();


    public EditMovieController() throws IOException {
    }


    public void setInfo(Movie selectedItem) {
        passedMovie = selectedItem;
        txt_title.setText(selectedItem.getTitle());
        txt_ImbdRating.setText(String.valueOf(selectedItem.getImdbRating()));
        txt_userRating.setText(String.valueOf(selectedItem.getUserRating()));
    }

    public void updateMovie (ActionEvent actionEvent) throws Exception {
        String title = txt_title.getText();
        Double imdbRating = Double.parseDouble(txt_ImbdRating.getText());
        Double userRating = Double.parseDouble(txt_userRating.getText());
        Date lastView = null;
        String movieUrl = txt_movieUrl.getText();

        Movie movieUpdated = new Movie(title, userRating, imdbRating, null, movieUrl, passedMovie.getId());
        manager.updateMovie(movieUpdated);
        Stage stage = (Stage) btnSaveMovie.getScene().getWindow();
        stage.close();

    }


    public void cancelEditingMovie(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Discard changes ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();


        if (alert.getResult() == ButtonType.YES) {
            Stage stage = (Stage) btnCancelEditingMovie.getScene().getWindow();
            stage.close();

        }
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
}
