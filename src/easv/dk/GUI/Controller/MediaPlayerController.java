package easv.dk.GUI.Controller;

import easv.dk.BE.Movie;
import easv.dk.GUI.Model.MovieModel;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javax.script.Bindings;
import java.io.File;
import java.io.IOException;
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
    MovieModel movieModel = new MovieModel();
    @FXML
    MediaView mediaView;

    private MediaPlayer mediaPlayer;
    private String movieUrl;

    public MediaPlayerController() throws IOException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //playMedia(movieUrl);
    }

    public void playMedia() {
     if (movieUrl != null){
         Media media = new Media(new File(movieUrl).toURI().toString());
         mediaPlayer = new MediaPlayer(media);
         mediaView.setMediaPlayer(mediaPlayer);

         mediaPlayer.play();
     }
    }
    public void choose(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        movieUrl = file.toURI().toString();

        if (movieUrl != null) {
            Media media = new Media(movieUrl);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);

            mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> timeSlider.setValue(newValue.toSeconds()));

            timeSlider.setOnMousePressed(event -> mediaPlayer.seek(Duration.seconds(timeSlider.getValue())));
            timeSlider.setOnMouseDragged(event -> mediaPlayer.seek(Duration.seconds(timeSlider.getValue())));

            mediaPlayer.setOnReady(() -> {
                Duration total = media.getDuration();
                timeSlider.setMax(total.toSeconds());
            });
            volSlider.setValue(mediaPlayer.getVolume() * 100);
            volSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volSlider.getValue()/100);

                }
            });



        }

    }
    int pressed = 0;
    public void playPause(ActionEvent actionEvent) {
           pressed++;
        if(pressed == 2)
        {
            mediaPlayer.pause();
            playPauseBtn.setText("▶");
            pressed = 0;
        }
        if (pressed == 1) {
            playMedia();
            mediaPlayer.play();
            playPauseBtn.setText("⏸");
        }


    }
    boolean speedDown = false;
    boolean speedup = false;

    public void speedDown(ActionEvent actionEvent) {
        if(speedup == true) {
            mediaPlayer.setRate(1);
        speedup = false;}
        else
        mediaPlayer.setRate(0.5); speedDown = true;

    }

    public void speedUp(ActionEvent actionEvent) {
        if(speedDown == true){
        mediaPlayer.setRate(1);
        speedDown = false;}
        else
            mediaPlayer.setRate(2); speedup = true;
    }


    public void setInfo(Movie selectedItem) {
        movieUrl = selectedItem.getMovieUrl();
    }
}
