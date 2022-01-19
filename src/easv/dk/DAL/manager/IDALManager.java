package easv.dk.DAL.manager;

import easv.dk.BE.Category;
import easv.dk.BE.Movie;

import java.sql.SQLException;
import java.util.List;

public interface IDALManager {
    public List<Movie> getAllMovies() throws SQLException;

    Movie createMovie(Movie movie) throws Exception;

    public List<Category> getAllCategories() throws SQLException;

    public Category createCategory(String category) throws Exception;

    public void deleteMovie(Movie movie) throws SQLException;


    public void deleteCategory(Category category) throws SQLException;

    public List<Category> getCategoriesFromMovies(Movie movie) throws SQLException;

    public List<Movie> getMoviesFromCategories(Category category) throws SQLException;

    public void updateMovie(Movie movie) throws SQLException;

    public void updateCategory(String name, int id) throws SQLException;

    public void addMovieToCategory(Category category,Movie movie) throws SQLException;

    public void removeMovieFromCategory(Category category,Movie movie) throws SQLException;
}
