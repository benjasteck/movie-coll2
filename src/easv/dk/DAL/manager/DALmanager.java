package easv.dk.DAL.manager;

import easv.dk.BE.Category;
import easv.dk.BE.Movie;
import easv.dk.DAL.CatMovieDAO;
import easv.dk.DAL.CategoryDAO;
import easv.dk.DAL.MovieDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DALmanager implements IDALManager {
    private static CategoryDAO categoryDAO;
    private MovieDAO movieDAO;
    private CatMovieDAO catMovieDAO;

    public DALmanager() throws IOException {
        this.categoryDAO = new CategoryDAO();
        this.movieDAO = new MovieDAO();
        this.catMovieDAO = new CatMovieDAO();
    }


    public List<Movie> getAllMovies() throws SQLException {

        return movieDAO.getAllMovies();
    }

    @Override
    public Movie createMovie(Movie movie) throws Exception {
        return movieDAO.createMovie(movie);
    }

    @Override
    public List<Category> getAllCategories() throws SQLException {
        return categoryDAO.getAllCategories();
    }


    public Category createCategory(String category) throws Exception {

        return categoryDAO.createNewCategory(category);
    }

    @Override
    public void updateCategory(String name, int id) throws SQLException {
        categoryDAO.updateCategory(new Category(id,name));
    }

    @Override
    public List<Category> getCategoriesFromMovies(Movie movie) throws SQLException {
        return catMovieDAO.getAllCategoriesForGivenMovie(movie);
    }

    @Override
    public List<Movie> getMoviesFromCategories(Category category) throws SQLException {
        return catMovieDAO.getMovieFromCategory(category);
    }

    @Override
    public Movie updateMovie(Movie movie) throws SQLException {
         return movieDAO.updateMovie(movie);

    }


    public void addMovieToCategory(Category category,Movie movie) throws SQLException {
        catMovieDAO.AddCategoryToMovie(category,movie);
    }

    public void removeMovieFromCategory(Category category,Movie movie) throws SQLException {
        catMovieDAO.removeCategoryFromMovie(category,movie);
    }

    public void deleteCategory(Category selectedItem) throws SQLException {
        categoryDAO.deleteCategory(selectedItem);
    }

    public void deleteMovie(Movie selectedItem) throws SQLException {
        movieDAO.deleteMovie(selectedItem);
    }

    public void saveRating(Movie movie) throws SQLException {
        movieDAO.saveRating(movie);

    }

}
