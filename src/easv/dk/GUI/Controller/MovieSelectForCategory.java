package easv.dk.GUI.Controller;

import easv.dk.BE.Category;
import easv.dk.BE.Movie;
import easv.dk.BLL.LogicInterface;
import easv.dk.BLL.Manager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieSelectForCategory {
    private Category category;
    private Controller controller;
    @FXML
    private Button movieSelectFormMovieOkButton;

    @FXML
    private ComboBox<Movie> movieCombo;

    @FXML
    private Button movieSelectForMovieCancelButton;

    @FXML
    private Label categoryIdLabel;

    @FXML
    private Label categoryNameLabel;

    @FXML
    public void initialize() {
        movieSelectForMovieCancelButton.setOnAction(event -> {
            movieSelectForMovieCancelButton.getScene().getWindow().hide();
        });
        movieSelectFormMovieOkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    final Movie selectedMovie = movieCombo.getSelectionModel().getSelectedItem();
                    if (selectedMovie==null)
                        return;
                    LogicInterface bll=new Manager();
                    int selectedMovieId = selectedMovie.getId();
                    bll.addMovieToCategory(selectedMovieId,category.getId());
                    movieSelectFormMovieOkButton.getScene().getWindow().hide();
                    if (controller!=null)
                        controller.initialize();    //to refresh data in tables
                } catch (IOException exception) {
                    exception.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
    }

    private void fillMovieCombo() {
        try {
            LogicInterface bll = new Manager();
            List<Movie> categoryMovies = bll.getMoviesFromCategories(category);  //get current movie category
            List<Movie> allMovies = bll.getAllMovies();          //get all cateogries
            List<Movie> showingMovies = getShowingMovies(categoryMovies, allMovies);

            movieCombo.getItems().clear();
            movieCombo.getItems().addAll(showingMovies);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Movie> getShowingMovies(List<Movie> categoryMovies, List<Movie> allMovies) {
        List<Movie> showingMovies = new ArrayList<>();
        boolean found;
        for (Movie movie : allMovies) {        //remove current movie categories from all categories
            found = false;
            for (int i = 0; i < categoryMovies.size(); i++) {
                if (categoryMovies.get(i).getId() == movie.getId() && categoryMovies.get(i).getTitle().equals(movie.getTitle()))
                    found = true;
            }
            if (!found)
                showingMovies.add(movie);
        }
        return showingMovies;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        categoryIdLabel.setText(category.getId() + "");
        categoryNameLabel.setText(category.getName());
        fillMovieCombo();
    }

    public void setParentController(Controller controller) {
        this.controller=controller;
    }
}
