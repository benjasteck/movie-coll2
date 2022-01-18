package easv.dk.GUI.Controller;


import easv.dk.BE.Movie;
import easv.dk.BLL.Manager;
import easv.dk.GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;


public class RateMovieController {

    @FXML
    private Button btnCancelRatingMovie;
    @FXML
    private Button btnSaveRating;
    @FXML
    private Label lblTitle;
    Manager manager = new Manager();
    @FXML
    private TableView<Movie> movieTable;
    @FXML
    private Rating boxRating;

    Controller mainController;



    public RateMovieController() throws IOException {
    }

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


    public void saveRating(ActionEvent actionEvent) throws Exception {
        Double name = boxRating.getRating();

        manager.saveRating(name);
        mainController.movieTable.refresh();
        Stage stage = (Stage) btnSaveRating.getScene().getWindow();
        stage.close();

    }

    public void setController(Controller controller) {
        this.mainController = controller;
    }
}

