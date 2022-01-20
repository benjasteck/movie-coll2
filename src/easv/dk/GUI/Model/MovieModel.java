package easv.dk.GUI.Model;

import easv.dk.BE.Movie;

import easv.dk.BLL.Manager;
import easv.dk.GUI.Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MovieModel {

    ObservableList<Movie> getAllMovies;
    Manager manager = new Manager();

    public MovieModel() throws IOException {
        getAllMovies = FXCollections.observableArrayList();
    }

    public void deleteMovie(Movie selectedItem) throws SQLException {
        manager.deleteMovie(selectedItem);
    }

    public void setGetAllMovies(ObservableList<Movie> getAllMovies) {
        this.getAllMovies = getAllMovies;
    }

    public List<Movie> getAllMovies1() throws SQLException, IOException {
        this.getAllMovies = FXCollections.observableArrayList();
        this.getAllMovies.addAll(this.manager.getAllMovies());
        return this.getAllMovies;
    }
}
