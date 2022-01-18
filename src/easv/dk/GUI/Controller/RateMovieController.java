package easv.dk.GUI.Controller;


import easv.dk.BE.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;


public class RateMovieController {

    @FXML
    private Button btnCancelRatingMovie;
    @FXML
    private Button btnSaveRating;
    @FXML
    private Label lblTitle;

    public void cancelRatingMovie(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Discard changes ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();


        if (alert.getResult() == ButtonType.YES) {
            Stage stage = (Stage) btnCancelRatingMovie.getScene().getWindow();
            stage.close();


        }
    }

    public void setInfo(Movie selectedItem) {
        lblTitle.setText(selectedItem.getTitle());

    }

    public void saveRating(ActionEvent actionEvent) {

    }
}
