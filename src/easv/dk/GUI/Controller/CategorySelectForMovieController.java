package easv.dk.GUI.Controller;

import easv.dk.BE.Category;
import easv.dk.BE.Movie;
import easv.dk.BLL.LogicInterface;
import easv.dk.BLL.Manager;
import easv.dk.DAL.manager.IDALManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorySelectForMovieController {
    private Movie movie;
    private int selectedCategoryId;
    private Controller controller;


    public int getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public void setSelectedCategoryId(int selectedCategoryId) {
        this.selectedCategoryId = selectedCategoryId;
    }

    @FXML
    private Button CategorySelectFormMovieOkButton;

    @FXML
    private ComboBox<Category> categoryCombo;

    @FXML
    private Button categorySelectForMovieCancelButton;

    @FXML
    private Label movieIdLabel;

    @FXML
    private Label movieNameLabel;

    @FXML
    public void initialize() {
        categorySelectForMovieCancelButton.setOnAction(event -> {
            categorySelectForMovieCancelButton.getScene().getWindow().hide();
        });
        CategorySelectFormMovieOkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    final Category selectedCategory = categoryCombo.getSelectionModel().getSelectedItem();
                    if (selectedCategory==null)
                        return;
                    LogicInterface bll=new Manager();
                    int selectedCategoryId = selectedCategory.getId();
                    bll.addMovieToCategory(movie.getId(),selectedCategoryId);       //add to catmovie table in database
                    CategorySelectFormMovieOkButton.getScene().getWindow().hide();// hide this window
                    controller.initialize();        //to refresh main controller data
                } catch (IOException exception) {
                    exception.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
    }
//load data and fill in combo box
    private void fillCategoryCombo() {
        try {
            LogicInterface bll = new Manager();
            List<Category> movieCategories = bll.getCategoriesFromMovie(movie);  //get current movie category
            List<Category> allCategories = bll.getAllCategories();          //get all cateogries
            List<Category> showingCategories = getShowingCategories(movieCategories, allCategories);

            categoryCombo.getItems().clear();
            categoryCombo.getItems().addAll(showingCategories);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//get all categories and remove current categories and show the others
    private List<Category> getShowingCategories(List<Category> movieCategories, List<Category> allCategories) {
        List<Category> showingCategories = new ArrayList<>();
        boolean found = false;
        for (Category category : allCategories) {        //remove current movie categories from all categories
            found = false;
            for (int i = 0; i < movieCategories.size(); i++) {
                if (movieCategories.get(i).getId() == category.getId() && movieCategories.get(i).getName().equals(category.getName()))
                    found = true;
            }
            if (!found)
                showingCategories.add(category);
        }
        return showingCategories;
    }

    public Movie getMovie() {
        return movie;
    }
//fill labels and combo
    public void setMovie(Movie movie) {
        this.movie = movie;
        movieIdLabel.setText(movie.getId() + "");
        movieNameLabel.setText(movie.getTitle());
        fillCategoryCombo();
    }

    public void setParentController(Controller controller) {
        this.controller=controller;
    }
}
