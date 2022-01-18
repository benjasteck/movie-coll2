package easv.dk.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.dk.BE.Category;
import easv.dk.BE.Movie;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    easv.dk.DAL.ConnectionManager cm;

    public CategoryDAO() throws IOException {
        cm = new easv.dk.DAL.ConnectionManager();
    }

    public Category createNewCategory(Category category) throws Exception {
        Category createdCategory = null;
        Connection con = cm.getConnection();
        String sqlSelectCategory = "INSERT INTO category VALUES(?)";        //id will be set in database automatically

        PreparedStatement pststmtInsertCategory =
                con.prepareStatement(sqlSelectCategory, Statement.RETURN_GENERATED_KEYS);       //prepared statement is for set query parameters and run query in database
        pststmtInsertCategory.setString(1, category.getName());

        pststmtInsertCategory.execute();
        ResultSet rs = pststmtInsertCategory.getGeneratedKeys();  //created identity id return
        //result set is for getting data from executed query
        while (rs.next()) {             //traverse results in result set and get data
            int id = rs.getInt(1);
            createdCategory = new Category(id,  category.getName());
        }
        rs.close();                         //closing all used objects
        pststmtInsertCategory.close();
        con.close();
        return createdCategory;


    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        Connection con = cm.getConnection();
        String sqlSelectCategory = "SELECT * FROM category;";
        PreparedStatement pststmtmtselectCategory = con.prepareStatement(sqlSelectCategory);         //prepared statement is for set query parameters and run query in database
        ResultSet rs = pststmtmtselectCategory.executeQuery();          //result set is for getting data from executed query

        while (rs.next()) {              //traverse results in result set and get data
            String name = rs.getString("name");
            int Id = rs.getInt("id");
            Category category = new Category( Id,name);
            categoryList.add(category);
        }
        //closing all used objects
        rs.close();
        pststmtmtselectCategory.close();
        con.close();
        return categoryList;
    }
    public Category getCategoryById(int id) throws SQLException {  //to get a single category
        Category category=null;
        Connection con=cm.getConnection();
        String query="select * from category where id=?;";      //select category by Id
        PreparedStatement preparedStatement=con.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet= preparedStatement.executeQuery();

        while (resultSet.next()){           //set retrieved data to category object
            category=new Category(resultSet.getInt("id"),resultSet.getString("name"));
        }
        resultSet.close();          //closed used objects
        preparedStatement.close();
        con.close();
        return category;
    }

    public void updateCategory(Category category) throws SQLException {
        Connection con = cm.getConnection();
        String sqlUpdateCategory = "UPDATE category SET name=? WHERE ID=?;";
        PreparedStatement pststmtUpdateCategory = con.prepareStatement(sqlUpdateCategory, Statement.RETURN_GENERATED_KEYS);   //prepared statement is for set query parameters and run query in database
        pststmtUpdateCategory.setString(1, category.getName());     //set parameters of query
        pststmtUpdateCategory.setInt(2, category.getId());
        pststmtUpdateCategory.executeUpdate();      //run query without getting data from database
        pststmtUpdateCategory.close();  //closing all used objects
        con.close();
    }


    public void deleteCategory(Category category) throws SQLException {
        Connection con = cm.getConnection();
        String sqlDeleteCategory = "DELETE FROM category WHERE ID=?;";
        PreparedStatement pststmtDeleteCategory = con.prepareStatement(sqlDeleteCategory, Statement.RETURN_GENERATED_KEYS);  //prepared statement is for set query parameters and run query in database
        pststmtDeleteCategory.setInt(1, category.getId()); //set query parameter
        pststmtDeleteCategory.executeUpdate();
        pststmtDeleteCategory.close();
        con.close();
    }
}

