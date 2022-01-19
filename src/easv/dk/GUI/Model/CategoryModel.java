package easv.dk.GUI.Model;

import easv.dk.BE.Category;
import easv.dk.BE.Movie;
import easv.dk.BLL.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CategoryModel {

    ObservableList<Category> getAllCategories;

    public CategoryModel() throws IOException {
        getAllCategories = FXCollections.observableArrayList();
        setListCategory();
    }
    Manager manager = new Manager();


    private void setListCategory() {
        //  getAllCategories.setAll(Manager.getAllCategories);
    }



    public void setGetAllCategories(ObservableList<Category> getAllCategories) {
        this.getAllCategories = getAllCategories;
    }

    public ObservableList<Category> getAllCategories1() throws SQLException {
        this.getAllCategories = FXCollections.observableArrayList();
        this.getAllCategories.addAll(this.manager.getAllCategories());
        return this.getAllCategories;
    }

    public void deleteCategory(Category selectedItem) throws SQLException {
        manager.deleteCategory(selectedItem);
    }

    public void addToCategory(Category selectedItem, int selectedIndex, Movie selectedMovie) throws SQLException {
        manager.addMovieToCategory(selectedItem, selectedMovie);
        List<Movie> newList = selectedItem.getMoviesInCategory();
        newList.add(selectedMovie);
        selectedItem.setAllMoviesInCategory(newList);
        selectedItem.setMovieCount(selectedItem.getMovieCount() + 1);
        getAllCategories.set(selectedIndex, selectedItem);

    }
}
