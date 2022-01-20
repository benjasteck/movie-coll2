package easv.dk.GUI.Controller;

import easv.dk.BE.CatMovie;
import easv.dk.BE.Category;
import easv.dk.BE.Movie;
import easv.dk.BLL.LogicInterface;
import easv.dk.BLL.Manager;
import easv.dk.DAL.CatMovieDAO;
import easv.dk.GUI.Model.CategoryModel;
import easv.dk.GUI.Model.MovieModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {


    public ListView movieInCategory;
    public Button btnMovieToCat;
    @FXML
    private TextField searchBar;
    private final ObservableList<Movie> dataList = FXCollections.observableArrayList();
    private final ObservableList<Category> dataList2 = FXCollections.observableArrayList();

    @FXML
    private TableView categoryTable;
    @FXML
    public TableView<Movie> movieTable;
    @FXML
    private Button btnNewCategory;
    @FXML
    private Button btnEditCategory;
    @FXML
    private Button btnDeleteCategory;
    @FXML
    private Button btnRemoveMovies;
    @FXML
    private Button btnAddCatMovies;
    @FXML
    private Button btnDeleteMovies;
    @FXML
    private Button btnEditMovies;
    @FXML
    private Button btnNewMovie;
    @FXML
    private Button btnCloseApplication;
    @FXML
    private Button btnRateMovie;
    @FXML
    private Button btnPlayMovie;
    @FXML
    private Button btnAscendDescend;
    @FXML
    private ComboBox sorterBox;
    public int pressed = 0;
    private final static int MovieSelected = 0;   //constant
    private final static int CategorySelected = 1;        //constant
    private int mode = MovieSelected;

    MovieModel movieModel = new MovieModel();
    CategoryModel categoryModel = new CategoryModel();

    public Controller() throws IOException {
    }

    @FXML
    public void initialize() throws SQLException, IOException {
        sorterBox.getItems().removeAll(sorterBox.getItems());
        sorterBox.getItems().addAll("Title", "IMBD Score", "Category");

        setUpMovieTable();
        setUpCategoryTable();
        filter();
        filterCat();
        movieTable.setOnMouseClicked(event -> showMovieCategoriesInList());  //show categories when movie table clicked
        categoryTable.setOnMouseClicked(event -> showCategoryMoviesInList()); //show movies when category table clicked
    }


    public void openNewMovieWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("easv/dk/GUI/View/movieWindow.fxml"));
        Parent root = loader.load();
        MovieController movieController = loader.getController();
        movieController.setParentController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("New Movie");
        stage.centerOnScreen();
        stage.show();
    }

    public void openRateMovieWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("easv/dk/GUI/View/rateMovieWindow.fxml"));
        Parent root = loader.load();
        RateMovieController contrl = loader.getController();
        contrl.setInfo(movieTable.getSelectionModel().getSelectedItem());
        contrl.setController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Rate Movie");
        stage.centerOnScreen();
        stage.show();
    }


    public void editCategory(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("easv/dk/GUI/View/movieWindow.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Edit Category");
        stage.centerOnScreen();
        stage.show();
    }

    public void deleteCategory(ActionEvent actionEvent) throws SQLException {
        categoryModel.deleteCategory((Category) categoryTable.getSelectionModel().getSelectedItem());
        categoryTable.getItems().remove(categoryTable.getSelectionModel().getSelectedItem());
    }

    public void deleteMovies(ActionEvent actionEvent) throws SQLException {
        movieModel.deleteMovie(movieTable.getSelectionModel().getSelectedItem());
        movieTable.getItems().remove(movieTable.getSelectionModel().getSelectedItem());
    }

    public void editMovies(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("easv/dk/GUI/View/EDITmovieWindow.fxml"));
        Parent root = loader.load();

        EditMovieController control = loader.getController();
        control.setInfo(movieTable.getSelectionModel().getSelectedItem());
        control.setParentController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Edit Movie");
        stage.centerOnScreen();
        stage.show();

    }


    public void closeApplication(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Close the Application ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {

            Stage stage = (Stage) btnCloseApplication.getScene().getWindow();
            stage.close();
        }
    }

    public void setUpMovieTable() throws SQLException, IOException {
        TableColumn<Movie, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Movie, String> column2 = new TableColumn<>("IMDB Rating");
        column2.setCellValueFactory(new PropertyValueFactory<>("imdbRating"));
        TableColumn<Movie, String> column3 = new TableColumn<>("User Rating");
        column3.setCellValueFactory(new PropertyValueFactory<>("userRating"));
        TableColumn<Movie, String> column4 = new TableColumn<>("Last Viewed");
        column4.setCellValueFactory(new PropertyValueFactory<>("lastView"));
        TableColumn<Movie, String> column5 = new TableColumn<>("Category");
        column5.setCellValueFactory(new PropertyValueFactory<>("category"));
        movieTable.getColumns().clear();

        movieTable.getColumns().add(column1);
        movieTable.getColumns().add(column5);
        movieTable.getColumns().add(column2);
        movieTable.getColumns().add(column3);
        movieTable.getColumns().add(column4);
        movieTable.getItems().clear();
        movieTable.getItems().addAll(movieModel.getAllMovies1());

    }

    public void setUpCategoryTable() throws SQLException {
        TableColumn<Category, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Category, String> column2 = new TableColumn<>("Total Movies");
        column2.setCellValueFactory(new PropertyValueFactory<>("movieCount"));
        categoryTable.getColumns().clear();
        categoryTable.getColumns().add(column1);
        categoryTable.getColumns().add(column2);
        categoryTable.getItems().clear();
        categoryTable.getItems().addAll(categoryModel.getAllCategories1());
    }

    public void filter() throws SQLException, IOException {
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                dataList.addAll(movieModel.getAllMovies1()); //<-- depending on what name the method gets
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FilteredList<Movie> filteredData = new FilteredList<>(dataList, b -> true);
            filteredData.setPredicate(movie -> {
                // If filter text is empty, display all song.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare title, category and rating of every song with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                //List<Integer> result = (List<Integer>) filteredData.stream().filter(val -> val.intValue() > searchBar.textProperty()).collect(Collectors.toList());
                if (movie.getTitle().toLowerCase().contains(lowerCaseFilter))
                    return true; // Filter title.

                else return String.valueOf(movie.getImdbRating()).contains(lowerCaseFilter);

            });
            SortedList<Movie> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(movieTable.comparatorProperty());
            //show the new list of filtered songs
            movieTable.setItems(sortedData);
        });
    }

    public void filterCat() throws SQLException, IOException {
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                dataList2.addAll(categoryModel.getAllCategories1()); //<-- depending on what name the method gets
            } catch (SQLException e) {
                e.printStackTrace();
            }
            FilteredList<Category> filteredData = new FilteredList<>(dataList2, b -> true);
            filteredData.setPredicate(category -> {
                // If filter text is empty, display all song.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare title, category and rating of every song with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                return category.getName().toLowerCase().contains(lowerCaseFilter); // Filter title.

            });
            SortedList<Category> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(categoryTable.comparatorProperty());
            //show the new list of filtered songs
            categoryTable.setItems(sortedData);
        });
    }

    public void sort() {
        //constructors of the columns needed
        TableColumn movCol1 = movieTable.getColumns().get(1);
        TableColumn movCol2 = movieTable.getColumns().get(2);
        TableColumn catCol1 = (TableColumn) categoryTable.getColumns().get(0);

        //checks for selected colum
        if (sorterBox.getValue() == ("Title")) {
            //checks for whether it should be ascending or descending
            if (pressed == 1) {
                movCol1.setSortType(TableColumn.SortType.ASCENDING);
                movieTable.getSortOrder().add(movCol1);
                movieTable.sort();
            }
            if (pressed == 2) {
                movCol1.setSortType(TableColumn.SortType.DESCENDING);
                movieTable.getSortOrder().add(movCol1);
                movieTable.sort();
            }
        }
        if (sorterBox.getValue() == ("IMBD Score")) {
            //checks for whether it should be ascending or descending
            if (pressed == 1) {
                movCol2.setSortType(TableColumn.SortType.ASCENDING);
                movieTable.getSortOrder().add(movCol2);
                movieTable.sort();
            }
            if (pressed == 2) {
                movCol2.setSortType(TableColumn.SortType.DESCENDING);
                movieTable.getSortOrder().add(movCol2);
                movieTable.sort();
            }
        }
        if (sorterBox.getValue() == ("Category")) {
            //checks for whether it should be ascending or descending
            if (pressed == 1) {
                catCol1.setSortType(TableColumn.SortType.ASCENDING);
                categoryTable.getSortOrder().add(catCol1);
                categoryTable.sort();
            }
            if (pressed == 2) {
                catCol1.setSortType(TableColumn.SortType.DESCENDING);
                categoryTable.getSortOrder().add(catCol1);
                categoryTable.sort();
            }
        }
    }


    public int getBtnAscendDescend(ActionEvent actionEvent) {
        //allows the program to know when to sort by ascending order, descending order and when not to sort.
        boolean ascending = false;
        pressed++;
        //if button pressed once sort in ascending order
        if (pressed == 1)
            ascending = true;
        //if twice sort in descending order
        if (pressed == 2)
            ascending = false;
        //if pressed thrice set value to 0
        if (pressed == 3)
            pressed = 0;
        //if it is anything but zero run the sort method if not clear the sort and return table to original.
        if (pressed != 0)
            sort();
        else {
            movieTable.getSortOrder().clear();
            categoryTable.getSortOrder().clear();
        }
        return pressed;


    }

    public void openNewCategoryWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("easv/dk/GUI/View/categoryWindow.fxml"));

        Parent root = loader.load();
        CategoryController categoryController = loader.getController();
        categoryController.setParentController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("New Category");
        stage.centerOnScreen();
        stage.show();
    }


    public void addMovieToCategory(ActionEvent actionEvent) {
        if (categoryTable.getSelectionModel().getSelectedIndex() != -1 && movieTable.getSelectionModel().getSelectedIndex() != -1) {
            try {
                categoryModel.addToCategory((Category) categoryTable.getSelectionModel().getSelectedItem(), categoryTable.getSelectionModel().getSelectedIndex(), movieTable.getSelectionModel().getSelectedItem());
            } catch (Exception ex) {
            }
        }
    }

    public void testCatMovie(ActionEvent actionEvent) throws SQLException {
        CatMovieDAO catMov = new CatMovieDAO();
        List<CatMovie> list = new ArrayList<>();

        list.add(new CatMovie(1, 1));
        list.add(new CatMovie(2, 1));
        list.add(new CatMovie(3, 1));

        catMov.createCategoryMovie(list);

        catMov.getAllCatMovies();
    }


    public void removeMovies(ActionEvent actionEvent) {
        Movie selectedMovie;
        Category selectedCategory;
        if (mode == MovieSelected) {
            selectedMovie = movieTable.getSelectionModel().getSelectedItem();
            selectedCategory = movieInCategory.getItems().size() > 0 ? (Category) movieInCategory.getItems().get(0) : null;
        } else {
            selectedMovie = movieInCategory.getItems().size() > 0 ? (Movie) movieInCategory.getItems().get(0) : null;
            selectedCategory = (Category) categoryTable.getSelectionModel().getSelectedItem();
        }
        removeMovieFromCategory(selectedMovie, selectedCategory);
        movieInCategory.getItems().clear();
    }

    private void removeMovieFromCategory(Movie selectedMovie, Category selectedCategory) {
        if (selectedMovie == null || selectedCategory == null) return;
        try {
            LogicInterface bll = new Manager();
            bll.removeMovieFromCategory(selectedMovie.getId(), selectedCategory.getId());
            initialize();
        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void showMovieCategoriesInList() {
        clearListView();
        mode = MovieSelected;
        Movie selectedMovie = movieTable.getSelectionModel().getSelectedItem();  //get selected movie in movie table
        try {
            LogicInterface bll = new Manager();  //get bll interface to use data from database
            List<Category> categories = bll.getCategoriesFromMovie(selectedMovie);      //load categories for selected movie
            movieInCategory.getItems().addAll(categories);          //add loaded categories to movieInCategory List
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearListView() {
        movieInCategory.getItems().clear();
    }

    public void showCategoryMoviesInList() {
        clearListView();
        mode = CategorySelected;
        Category selectedCategory = (Category) categoryTable.getSelectionModel().getSelectedItem();  //get selected category in category table
        try {
            LogicInterface bll = new Manager();  //get bll interface to use data from database
            List<Movie> movies = bll.getMoviesFromCategories(selectedCategory);      //load movies for selected category
            movieInCategory.getItems().addAll(movies);          //add loaded movies to movieInCategory List
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getSelectedItem() {
        Movie movie1 = movieTable.getSelectionModel().getSelectedItem();
        return movie1.getMovieUrl();
    }

    public void playMovie(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("easv/dk/GUI/View/mediaPlayer.fxml"));
        Parent root = loader.load();

        MediaPlayerController contrl = loader.getController();
        contrl.setInfo(movieTable.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Media Player");
        stage.centerOnScreen();
        stage.show();

    }
}






