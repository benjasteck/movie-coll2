package easv.dk.BLL;


import easv.dk.BE.Category;
import easv.dk.BE.Movie;
import easv.dk.DAL.manager.DALmanager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Manager implements LogicInterface {


    /*
        public Movie updateMovieRating(){}
        public Movie updateMovieDate(){}
        public void searchMovie (){}
        public void updateMovieRating(){}
        public void updateMovieDate(){}
        */
    DALmanager daLmanager = new DALmanager();
    private Object Category;

    public Manager() throws IOException {

    }

    public List<Movie> getAllMovies() throws SQLException {
        return this.daLmanager.getAllMovies();

    }

    @Override
    public Movie createMovie(Movie movie) throws Exception {
        // return daLmanager.createMovie(movie);
        return null;
    }

    @Override
    public List<Category> getAllCategories() throws SQLException {
        return daLmanager.getAllCategories();
    }


    @Override
    public Category createCategory(String name) throws Exception {
        return daLmanager.createCategory(name);
    }


    @Override
    public List<Movie> getMoviesFromCategories(Category category) throws SQLException {
        return daLmanager.getMoviesFromCategories(category);
    }

    @Override
    public List<Category> getCategoriesFromMovie(Movie movie) throws SQLException {
        return daLmanager.getCategoriesFromMovies(movie);
    }

    @Override
    public void updateMovie(Movie movie) throws SQLException {
        //daLmanager.updateMovie(movie);
    }

    @Override
    public void updateCategory(String name, int id) throws SQLException {

    }


    public void updateCategory(Category category) throws SQLException {
        //categoryDAO.updateCategory(category);
    }


    public void addMovieToCategory(int movieId, int categoryId) throws SQLException {
        daLmanager.addMovieToCategory(new Category(categoryId, ""), new Movie("", 0, 0, "", "", movieId));
    }

    public void removeMovieFromCategory(int movieId, int categoryId) throws SQLException {
        daLmanager.removeMovieFromCategory(new Category(categoryId, ""), new Movie("", 0, 0, "", "", movieId));
    }

    public void deleteCategory(Category selectedItem) throws SQLException {
        daLmanager.deleteCategory(selectedItem);
    }

    public void deleteMovie(Movie selectedItem) throws SQLException {
        daLmanager.deleteMovie(selectedItem);
    }

    @Override
    public void saveRating(Double rating) throws Exception {

    }

    @Override
    public void updateMovieRating(Movie rating) throws SQLException {
        daLmanager.updateMovie(rating);
    }
}



