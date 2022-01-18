package easv.dk.BLL;


import easv.dk.BE.Category;
import easv.dk.BE.Movie;
import easv.dk.DAL.CatMovieDAO;
import easv.dk.DAL.CategoryDAO;
import easv.dk.DAL.MovieDAO;
import easv.dk.DAL.manager.DALmanager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class  Manager implements easv.dk.BLL.LogicInterface {


/*
    public Movie updateMovieRating(){}
    public Movie updateMovieDate(){}
    public void searchMovie (){}
    public void updateMovieRating(){}
    public void updateMovieDate(){}
    */
  DALmanager daLmanager = new DALmanager();
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
       return this.daLmanager.getAllCategories();
    }



    public Category createCategory(Category category) throws Exception {
        //return daLmanager.createCategory(category);
        return null;
    }


    @Override
    public List<Movie> getMoviesFromCategories(Category category) throws SQLException {
        return daLmanager.getMoviesFromCategories(category);
    }

    @Override
    public List<Category> getCategoriesFromMovies(Category category) {
   //     return daLmanager.getCategoriesFromMovies(category);
        return null;
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

    public  void deleteCategory(Category selectedItem) throws SQLException{
        //DALmanager.deleteCategory(selectedItem);

    }
    public void deleteMovie(Movie movie) throws SQLException {
        daLmanager.deleteMovie(movie);
    }

    /*@Override
    public void deleteCategory(Category category) throws SQLException {

    }*/

    public void addMovieToCategory(){

    }

    public void removeMovieFromCategory(){

    }

}
