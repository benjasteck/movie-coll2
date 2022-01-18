package easv.dk.DAL;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import easv.dk.BE.Movie;


public class MovieDAO {

    easv.dk.DAL.ConnectionManager cm;

    public MovieDAO() throws IOException {
        cm = new easv.dk.DAL.ConnectionManager();
    }

    //Insert values to the movie table.
    public Movie createMovie(Movie movie) throws Exception {
        Movie movieCreated = null;
        Connection con = cm.getConnection();
        String sqlSelectMovie = "INSERT INTO MOVIE VALUES(?,?,?,?,?)";
        PreparedStatement pststmtInsertMovie = con.prepareStatement(sqlSelectMovie, Statement.RETURN_GENERATED_KEYS);

        pststmtInsertMovie.setString(1, movie.getTitle());
        pststmtInsertMovie.setDouble(2, movie.getUserRating());
        pststmtInsertMovie.setDouble(3, movie.getImdbRating());
        pststmtInsertMovie.setString(4, movie.getMovieUrl());
        pststmtInsertMovie.setString(5, movie.getLastView());
        pststmtInsertMovie.execute();
        ResultSet rs = pststmtInsertMovie.getGeneratedKeys();
        while (rs.next()) {

            movieCreated = new Movie(movie.getTitle(),
                    movie.getUserRating(),
                    movie.getImdbRating(),
                    movie.getLastView(),
                    movie.getMovieUrl(),
                    rs.getInt(1)
            );

        }
        return movieCreated;

    }

    public List<Movie> getAllMovies() throws SQLException {
        List<Movie> movieList = new ArrayList<>();
        Connection con = cm.getConnection();
        String sqlSelectMovie = "SELECT * FROM Movie;";
        PreparedStatement pstStatementSelectMovie = con.prepareStatement(sqlSelectMovie);
        ResultSet rs = pstStatementSelectMovie.executeQuery();

        while (rs.next()) {
            String title = rs.getString("title");
            Double userRating = rs.getDouble("userRating");
            double IMBDrating = rs.getDouble("imdbRating");
            String lastView = rs.getString("lastview");
            String movieUrl = rs.getString("fileLink");
            int Id = rs.getInt("id");
            Movie movie = new Movie(title, userRating, IMBDrating, lastView, movieUrl, Id);
            movieList.add(movie);
        }
        rs.close();
        pstStatementSelectMovie.close();
        con.close();

        return movieList;
    }

    public void updateMovie(Movie movie) throws SQLException {
        Connection con = cm.getConnection();
        String sqlUpdateMovie = "UPDATE  Movie SET title=?, userrating=?, filelink=?,lastview=? WHERE ID=?;";
        PreparedStatement pststmtUpdateMovie = con.prepareStatement(sqlUpdateMovie, Statement.RETURN_GENERATED_KEYS);
        pststmtUpdateMovie.setString(1, movie.getTitle());
        pststmtUpdateMovie.setDouble(2, movie.getUserRating());
        pststmtUpdateMovie.setString(3, movie.getMovieUrl());
        pststmtUpdateMovie.setString(4, movie.getLastView());
        pststmtUpdateMovie.setInt(5, movie.getId());
        pststmtUpdateMovie.executeUpdate();
        pststmtUpdateMovie.close();
        con.close();
    }

    public void deleteMovie(Movie movie) throws SQLException {
        Connection con = cm.getConnection();
        String sqlDeleteMovie = "DELETE FROM movie WHERE ID=?;";
        PreparedStatement pststmtDeleteMovie = con.prepareStatement(sqlDeleteMovie, Statement.RETURN_GENERATED_KEYS);
        pststmtDeleteMovie.setInt(1, movie.getId());
        pststmtDeleteMovie.execute();
        pststmtDeleteMovie.close();
        con.close();
    }

}
