package easv.dk.DAL;

import easv.dk.BE.CatMovie;
import easv.dk.BE.Category;
import easv.dk.BE.Movie;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatMovieDAO {
    ConnectionManager cm;

    public CatMovieDAO() {
        try {
            cm = new ConnectionManager();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createCategoryMovie(List<CatMovie> list) throws SQLException {
        Connection con = cm.getConnection();

        String sql = "INSERT INTO catMovie (category_id, movie_id) VALUES (?,?)";
        PreparedStatement statement = con.prepareStatement(sql);

        for (CatMovie cM : list) {          //insert each item of list in database
            statement.setInt(1, cM.getCategoryID());
            statement.setInt(2, cM.getMovieID());
            statement.executeUpdate();
        }
        statement.close();
        con.close();

    }

    public List<CatMovie> getAllCatMovies() throws SQLException {

        ArrayList<CatMovie> catMovies = new ArrayList<>();
        Connection con = cm.getConnection();

        String sql = "SELECT * FROM catMovie;";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            int idCat = rs.getInt("category_id");
            int idMov = rs.getInt("movie_id");
            CatMovie catMovie = new CatMovie(idCat, idMov);
            catMovies.add(catMovie);
        }
        rs.close();
        statement.close();
        con.close();
        return catMovies;
    }


    public CatMovie createCatMovie(CatMovie catMovie) throws SQLException {
        CatMovie catMovie1 = null;
        Connection con = cm.getConnection();
        String query = "insert into catmovie (movie_id,category_id) values(?,?);";
        PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);  //to return generated catMovie_id in result
        preparedStatement.setInt(1, catMovie.getMovieID());
        preparedStatement.setInt(2, catMovie.getCategoryID());
        preparedStatement.execute();        //execute insert query
        ResultSet resultSet = preparedStatement.getGeneratedKeys();     //get generated catMovie_id
        while (resultSet.next()) {
            catMovie1.setMovieID(catMovie.getMovieID());        //use input catmovi
            catMovie1.setCategoryID(catMovie.getCategoryID());  //use input cat movie
            catMovie1.setId(resultSet.getInt(1));       //get generated catMovie_id
        }
        resultSet.close();
        preparedStatement.close();
        con.close();

        return catMovie1;
    }

    public List<Movie> getMovieFromCategory(Category category) throws SQLException {
        List<Movie> movieList = new ArrayList<>();
        Connection con = cm.getConnection();
        String query = "select distinct movie.* from catMovie \n" +           //query to join movie table and catmovie table to get movie according to category id
                "JOIN movie on catMovie.movie_id=movie.id\n" +
                "where category_id=?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, category.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {           //set movie data
            String title = resultSet.getString("title");
            int id = resultSet.getInt("id");
            double userRating = resultSet.getDouble("userRating");
            double imdbRating = resultSet.getDouble("IMDBRating");
            String fileLink = resultSet.getString("fileLink");
            String lastView = resultSet.getString("lastVuew");
            Movie movie = new Movie(title, userRating, imdbRating, lastView, fileLink, id);
            movieList.add(movie);
        }
        resultSet.close();
        preparedStatement.close();
        con.close();
        return movieList;
    }


    public void updateCatMovie(CatMovie catMovie) throws SQLException {
        Connection con = cm.getConnection();
        String sqlUpdateCatMovie = "UPDATE catMovie SET categoty_Id=?, movie_Id=?, WHERE catmovie_id=?;";
        PreparedStatement pststmtUpdateCatMovie = con.prepareStatement(sqlUpdateCatMovie, Statement.RETURN_GENERATED_KEYS);
        pststmtUpdateCatMovie.setInt(1, catMovie.getCategoryID());
        pststmtUpdateCatMovie.setDouble(2, catMovie.getMovieID());
        pststmtUpdateCatMovie.setInt(3, catMovie.getId());
        pststmtUpdateCatMovie.executeUpdate();
        pststmtUpdateCatMovie.close();
        con.close();
    }

    public void deleteCatMovie(CatMovie catMovie) throws SQLException {
        Connection con = cm.getConnection();
        String sqlDeleteCatMovie = "DELETE FROM catMovie WHERE ID=?;";
        PreparedStatement pststmtDeleteCatMovie = con.prepareStatement(sqlDeleteCatMovie, Statement.RETURN_GENERATED_KEYS);
        pststmtDeleteCatMovie.setInt(1, catMovie.getId());
        pststmtDeleteCatMovie.execute();
        pststmtDeleteCatMovie.close();
        con.close();
    }

    public List<Movie> getAllMoviesForGivenCategory( Category category) throws SQLException {
        return getMovieFromCategory(category);
    }

    public List<Category> getAllCategoriesForGivenMovie(Movie movie) throws SQLException {
        List<Category> categories = new ArrayList<>();
        Connection con = cm.getConnection();
        String query = "select distinct category.* from catMovie \n" +           //query to join category table and catmovie table to get category according to movie id
                "JOIN category on catMovie.category_Id=category.id\n" +
                "where movie_id=?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, movie.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {           //set movie data
            String name = resultSet.getString("name");
            int id = resultSet.getInt("id");

            categories.add(new Category(id,name));
        }
        resultSet.close();
        preparedStatement.close();
        con.close();
        return categories;
    }

    public void AddCategoryToMovie(Category category,Movie movie) throws SQLException {
        List<Category> allCategoriesForGivenMovie = getAllCategoriesForGivenMovie(movie);
        if (allCategoriesForGivenMovie.contains(category))return;
        createCatMovie(new CatMovie(category.getId(),movie.getId()));
    }
    public void removeCategoryFromMovie(Category category, Movie movie) throws SQLException {
        Connection con = cm.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement("delete from catmovie where category_id=? and movie_id=?");  //delete row from catmovie table according to category and movie
        preparedStatement.setInt(1,category.getId());
        preparedStatement.setInt(2,movie.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        con.close();


    }
}

