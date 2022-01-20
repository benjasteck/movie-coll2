package easv.dk.GUI.Controller;

import easv.dk.BE.Category;
import easv.dk.BE.Movie;
import easv.dk.BLL.Manager;
import easv.dk.DAL.CategoryDAO;
import easv.dk.DAL.MovieDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;


public class CategoryController {
    private Controller parentController;
    public void setParentController(Controller controller){
        this.parentController=controller;
    }
    @FXML
    private Button btnCancelEditingCategory;
    @FXML
    private TextField txtName;
    @FXML
    private Button saveCategoryButton;
    @FXML
    private TableView categoryTable = new TableView();

    public CategoryController() throws IOException {
    }

    public void cancelEditingCategory(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Discard changes ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();


        if (alert.getResult() == ButtonType.YES) {

            Stage stage = (Stage) btnCancelEditingCategory.getScene().getWindow();
            stage.close();
        }
    }
    Manager manager = new Manager();

   public void saveCategory(ActionEvent actionEvent) throws Exception {
        String name = txtName.getText();

        manager.createCategory(name);
        Stage stage = (Stage) saveCategoryButton.getScene().getWindow();
        stage.close();
        categoryTable.refresh();
        parentController.initialize();
    }
}
