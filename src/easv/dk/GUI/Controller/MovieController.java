package easv.dk.GUI.Controller;

import easv.dk.BE.Category;
import easv.dk.BE.Movie;
import easv.dk.BLL.LogicInterface;
import easv.dk.BLL.Manager;
import easv.dk.DAL.MovieDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;




import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MovieController {
private Controller controller;
public void setParentController(Controller controller){
    this.controller=controller;
}

    @FXML
    private Button btnChooseFile;
    @FXML

    public Button btnCancelEditingMovie;

    @FXML
    private ComboBox comboBoxCategory;

    @FXML
    private Button btnSaveMovie;

    @FXML
    private TextField txt_title;

    @FXML
    private TextField txt_ImbdRating;

    @FXML
    private TextField txt_userRating;

    @FXML
    private TextField txt_movieUrl;



    @FXML
    public void cancelEditingMovie(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Discard changes ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();


        if (alert.getResult() == ButtonType.YES) {
            Stage stage = (Stage) btnCancelEditingMovie.getScene().getWindow();
            stage.close();


        }

    }

    public void chooseCategory(ActionEvent actionEvent) {
    }

    @FXML
    public void initialize() {

        //adds different Categories to the comboBox that the user can choose from

        fillCategoryCombo();
    }
     private void fillCategoryCombo() {
        try {
            LogicInterface bll = new Manager();  //interface that using Logic layer
            
            List<Category> allCategories = bll.getAllCategories();          //get all cateogries
            

            comboBoxCategory.getItems().clear();        //clear combo box last items
            comboBoxCategory.getItems().addAll(allCategories);      //add all database categories to combo box
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void chooseFile(ActionEvent actionEvent) {
        // double-clicking on the "choose" button will open another window with your computer´s files
        Window window = ((Node) (actionEvent.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Movie Files", "*.mp4", "*.mpeg4"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            txt_movieUrl.setText(file.getPath());

        } else  {
            System.out.println("error"); // or something else
        }

    }


    @FXML
    private void saveNewMovie(ActionEvent actionEvent) throws Exception {
        String title = txt_title.getText();
        Double imdbRating = Double.parseDouble(txt_ImbdRating.getText());
        Double userRating = Double.parseDouble(txt_userRating.getText());
        Date lastView = null;
        String movieUrl = txt_movieUrl.getText();

        Movie movieCreated = new Movie(title, userRating, imdbRating, null, movieUrl, 0);  //create new movie object
        Movie savedMovie=MovieDAO.createMovie(movieCreated);  ////save new movie to database in movie table
        LogicInterface bll=new Manager();       //new login layer manager
        bll.addMovieToCategory(((Category) comboBoxCategory.getSelectionModel().getSelectedItem()),savedMovie);//add movie to category and save it in catmovie table

        Stage stage = (Stage) btnSaveMovie.getScene().getWindow();
        stage.close();
        this.controller.initialize();       //initialize movietable and category table and refresh them
    }

}





