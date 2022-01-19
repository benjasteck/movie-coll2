package easv.dk.GUI.Controller;

import easv.dk.BE.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MediaPlayerController {
    @FXML
    Slider timeSlider;
    @FXML
    Slider volSlider;
    @FXML
    VBox vBoxUi;
    @FXML
    HBox hBoxUi;
    @FXML
    Button playPauseBtn;
    @FXML
    Button speedUpBtn;
    @FXML
    Button speedDownBtn;
    Controller controller;

    public void playPause(ActionEvent actionEvent) {

    }

    public void speedDown(ActionEvent actionEvent) {

    }

    public void speedUp(ActionEvent actionEvent) {

    }

    public void setMovieSelected(){
       Movie movie = controller.getSelectedItem();
    }
}
