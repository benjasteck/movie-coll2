package easv.dk.BLL;

import easv.dk.BE.Category;
import easv.dk.BE.Movie;

import java.sql.SQLException;
import java.util.List;

public interface LogicInterface {

    public List<Movie> getAllMovies() throws SQLException;

    Movie createMovie(Movie movie) throws Exception;

    public List<Category>  getAllCategories() throws SQLException;

    public Category createCategory(Category category) throws Exception;

    public void deleteMovie(Movie movie) throws SQLException;



    public void deleteCategory(Category category) throws SQLException;

    public List<Category> getCategoriesFromMovie(Movie movie) throws SQLException;

    public List<Movie> getMoviesFromCategories(Category category) throws SQLException;

    public void updateMovie(Movie movie) throws SQLException;

    public void updateCategory(String name, int id) throws SQLException;

    public void addMovieToCategory(int movieId,int categoryId) throws SQLException;

    public void removeMovieFromCategory(int movieId,int categoryId) throws SQLException;



}
