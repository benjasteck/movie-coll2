package easv.dk.GUI.Model;

import easv.dk.BE.Category;
import easv.dk.BLL.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

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

    public static void deleteCategory(Object selectedItem) throws SQLException {
        //Manager.deleteCategory(selectedItem);
    }

}