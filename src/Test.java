import easv.dk.BE.Category;
import easv.dk.BE.Movie;
import easv.dk.DAL.CategoryDAO;
import easv.dk.DAL.MovieDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        // createMovie();
        getAllMovie();
        // updateMovie();
        //deleteMovie();
        //System.out.println("?"+!connection.isClose());

        //createCateogry();
        // getAllCatgories();
        // getCategoryById();
        //updateCateogry();
        //deleteCategory();
    }

    private static void deleteCategory() {
        try {
            CategoryDAO categoryDAO = new CategoryDAO();
            categoryDAO.deleteCategory(new Category(6, ""));
            List<Category> allCategories = categoryDAO.getAllCategories();
            for (Category cat :
                    allCategories) {
                System.out.println(cat.getId() + "  " + cat.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateCateogry() {
        CategoryDAO categoryDAO = null;
        try {
            categoryDAO = new CategoryDAO();
            categoryDAO.updateCategory(new Category(8, "animation"));
            Category categoryById = categoryDAO.getCategoryById(8);
            System.out.println(categoryById.getId());
            System.out.println(categoryById.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void getCategoryById() {
        try {
            CategoryDAO categoryDAO = new CategoryDAO();
            Category categoryById = categoryDAO.getCategoryById(8);
            System.out.println(categoryById.getId());
            System.out.println(categoryById.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getAllCatgories() {
        try {
            CategoryDAO categoryDAO = new CategoryDAO();
            List<Category> categoryList = categoryDAO.getAllCategories();
            for (Category category :
                    categoryList) {
                System.out.println(category.getId() + " " + category.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createCateogry() {
        try {
            CategoryDAO categoryDAO = new CategoryDAO();
            Category createdCategory = categoryDAO.createNewCategory(new Category(0, "Action"));
            System.out.println(createdCategory.getId());
            System.out.println(createdCategory.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void createMovie() throws Exception {
        MovieDAO movieDAO = new MovieDAO();

        List<Movie> movieList = new ArrayList<>();

        Movie movie1 = new Movie("The Matrix", 4.5, 5, "2022/01/11", "url", 0);
        Movie movie2 = new Movie("john wick", 0, 7.5, "2022-02-07", "C://Movies/johnWick.mp4", 0);

        movieList.add(movie1);
        movieList.add(movie2);


        for (Movie myMovie : movieList) {
            System.out.println("movie name: " + myMovie.getTitle() + " Rating: " + myMovie.getImdbRating());
            movieDAO.createMovie(myMovie);
        }

    }

    public static void getAllMovie() throws SQLException, IOException {
        MovieDAO myAccessToMovieDatabase = new MovieDAO();
        List<Movie> lıstOfAllMovie = myAccessToMovieDatabase.getAllMovies();
        for (Movie movie : lıstOfAllMovie) {
            System.out.println("Movie name: " + movie.getTitle() + " rating: " + movie.getUserRating() + " category: " + movie.getCategory());
        }
    }

    public static void updateMovie() throws IOException, SQLException {
        MovieDAO movieDAO = new MovieDAO();
        Movie movie = new Movie("John wick 3", 3, 4.5, "2022/1/7", "C://Movies/johnWick3.mp4", 19);
        movieDAO.updateMovie(movie);
    }

    public static void deleteMovie() throws SQLException, IOException {
        MovieDAO songDAO = new MovieDAO();
        Movie movie = new Movie("SpiderMan2", 3, 4.5, "2022/1/7", "C://Movies/SpiderMan", 15);
        songDAO.deleteMovie(movie);
    }
}