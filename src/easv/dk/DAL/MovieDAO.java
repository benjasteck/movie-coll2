package easv.dk.DAL;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import easv.dk.BE.Movie;


public class MovieDAO {


    private static ConnectionManager cm;
    private Movie movie;


    public MovieDAO() throws IOException {
        cm = new ConnectionManager();
    }

    //Insert values to the movie table.
    public static Movie createMovie(Movie movie) throws Exception {
        Movie movieCreated = null;
        Connection con = cm.getConnection();
        String sqlSelectMovie = "INSERT INTO MOVIE VALUES(?,?,?,?,?)";
        PreparedStatement pststmtInsertMovie = con.prepareStatement(sqlSelectMovie, Statement.RETURN_GENERATED_KEYS);

        pststmtInsertMovie.setString(1, movie.getTitle());
        pststmtInsertMovie.setDouble(2, movie.getUserRating());
        pststmtInsertMovie.setDouble(3, movie.getImdbRating());
        pststmtInsertMovie.setString(4, movie.getMovieUrl());
        pststmtInsertMovie.setDate(5, movie.getLastView());
        pststmtInsertMovie.addBatch();
        pststmtInsertMovie.executeBatch();
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
        String sqlSelectMovie = "\n" +
                "select movie.id,title,userRating,IMDBRating,fileLink,lastview,CAST(count(category_id) as varchar(20)) as categories from movie\n" +
                "left join catMovie on movie.id=catMovie.movie_id\n" +
                "left join category on category.id=catMovie.category_id\n" +
                "GROUP by movie.id,title,userRating,IMDBRating,fileLink,lastview\n;";
        PreparedStatement pstStatementSelectMovie = con.prepareStatement(sqlSelectMovie);
        ResultSet rs = pstStatementSelectMovie.executeQuery();

        while (rs.next()) {
            String title = rs.getString("title");
            Double userRating = rs.getDouble("userRating");
            double IMBDrating = rs.getDouble("imdbRating");
            Date lastView = rs.getDate("lastview");
            String movieUrl = rs.getString("fileLink");
            int Id = rs.getInt("id");
            Movie movie = new Movie(title, userRating, IMBDrating, lastView, movieUrl, Id);
            movie.setCategory(rs.getString("categories"));
            movieList.add(movie);
        }
        rs.close();
        pstStatementSelectMovie.close();
        con.close();

        return movieList;
    }

    public Movie updateMovie(Movie movie) throws SQLException {
        Connection con = cm.getConnection();
        String sqlUpdateMovie = "UPDATE  MOVIE SET title=?, userrating=?, filelink=? WHERE ID=?;";
        PreparedStatement pststmtUpdateMovie = con.prepareStatement(sqlUpdateMovie, Statement.RETURN_GENERATED_KEYS);
        pststmtUpdateMovie.setString(1, movie.getTitle());
        pststmtUpdateMovie.setDouble(2, movie.getUserRating());
        pststmtUpdateMovie.setString(3, movie.getMovieUrl());
        pststmtUpdateMovie.setInt(4, movie.getId());
        pststmtUpdateMovie.executeUpdate();
        pststmtUpdateMovie.close();
        con.close();

        return movie;
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
