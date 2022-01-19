package easv.dk.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

public class MediaPlayerController implements Initializable {
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
    @FXML
    MediaView mediaView;


    private MediaPlayer mediaPlayer;

    public MediaPlayerController(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    playMedia();
    }

    public void playMedia() {
     if (movieUrl != null){
         Media media = new Media(movieUrl);
         mediaPlayer = new MediaPlayer(media);
         mediaView.setMediaPlayer(mediaPlayer);

         mediaPlayer.play();
     }
    }

    public void playPause(ActionEvent actionEvent) {
    System.out.println(movieUrl);
    }

    public void speedDown(ActionEvent actionEvent) {

    }

    public void speedUp(ActionEvent actionEvent) {

    }

    public void setMovieSelected(){
       String movieUrl = controller.getSelectedItem();
    }
    private String setMovieSelected.movieUrl;

}
