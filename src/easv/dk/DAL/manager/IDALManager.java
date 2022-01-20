package easv.dk.DAL.manager;

import easv.dk.BE.Category;
import easv.dk.BE.Movie;

import java.sql.SQLException;
import java.util.List;

public interface IDALManager {
    List<Movie> getAllMovies() throws SQLException;

    Movie createMovie(Movie movie) throws Exception;

    List<Category> getAllCategories() throws SQLException;

    Category createCategory(String category) throws Exception;

    void deleteMovie(Movie movie) throws SQLException;


    void deleteCategory(Category category) throws SQLException;

    List<Category> getCategoriesFromMovies(Movie movie) throws SQLException;

    List<Movie> getMoviesFromCategories(Category category) throws SQLException;

    Movie updateMovie(Movie movie) throws SQLException;

    void updateCategory(String name, int id) throws SQLException;

    void addMovieToCategory(Category category, Movie movie) throws SQLException;

    void removeMovieFromCategory(Category category, Movie movie) throws SQLException;

    void saveRating(Movie movie) throws SQLException;


}
