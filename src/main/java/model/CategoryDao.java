package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDao {

    public static boolean suggestCategory(String name){
        try {
            PreparedStatement checkStatement = DataBase.getConnection().prepareStatement("SELECT * FROM categories WHERE name = \"" + name + "\"");
            ResultSet rs = checkStatement.executeQuery();
            if(rs.next()) return false;
            PreparedStatement insertStatement = DataBase.getConnection().prepareStatement("INSERT INTO categories(name,is_approved) values(\"" + name + "\", false)");
            insertStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<Category> getCategories(boolean status){
        ArrayList<Category> categories = new ArrayList<>();
        try {
            PreparedStatement getStatement = DataBase.getConnection().prepareStatement("SELECT * FROM categories WHERE is_approved is " + status);
            ResultSet rs = getStatement.executeQuery();
            generateCategoryList(rs, categories);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }


    public static void changeCategoryStatus(String name, boolean status){
        try {
            PreparedStatement changeStatement = DataBase.getConnection().prepareStatement("UPDATE categories SET is_approved = \"" + status + "\" WHERE name = \"" + name + "\"");
            changeStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteCategory(String name){
        try {
            PreparedStatement deleteStatement = DataBase.getConnection().prepareStatement("DELETE FROM categories WHERE name = \"" + name + "\"");
            deleteStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void generateCategoryList(ResultSet rs, ArrayList<Category> categories){
        try {
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                boolean isApproved = rs.getBoolean("is_approved");
                Category curr = new Category(id, name, isApproved);
                categories.add(curr);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

}

