package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDao {

    public static boolean suggestCategory(String name){
        if (categoryExists(name)){
            return false;
        }
        try {
            PreparedStatement suggestStatement = DataBase.getConnection().
                    prepareStatement("INSERT INTO categories(name,is_approved) values(\"" + name + "\", false)");
            suggestStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static ArrayList<Category> getCategories(boolean status){
        ArrayList<Category> categories = new ArrayList<>();
        try {
            PreparedStatement getStatement = DataBase.getConnection().
                    prepareStatement("SELECT * FROM categories WHERE is_approved is " + status);
            ResultSet rs = getStatement.executeQuery();
            generateCategoryList(rs, categories);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }


    public static void changeCategoryStatus(String name, boolean status){
        try {
            PreparedStatement changeStatement = DataBase.getConnection().
                    prepareStatement("UPDATE categories SET is_approved = " + status +  " WHERE name = \"" + name + "\"");
            changeStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteCategory(String name){
        try {
            PreparedStatement deleteStatement = DataBase.getConnection().
                    prepareStatement("DELETE FROM categories WHERE name = \"" + name + "\"");
            deleteStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int getCategoryIdByName(String name){
        int id = -1;
        try {
            PreparedStatement getIdStatement = DataBase.getConnection().
                    prepareStatement("SELECT id FROM categories WHERE name = \"" + name + "\"");
            ResultSet rs = getIdStatement.executeQuery();
            if (rs.next()){
              id = rs.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public static boolean categoryExists(String name){
        try {
            PreparedStatement checkStatement = DataBase.getConnection().
                    prepareStatement("SELECT * FROM categories WHERE name = \"" + name + "\"");
            ResultSet rs = checkStatement.executeQuery();
            if (rs.next()) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static String getCategoryNameById(int id){
        try {
            PreparedStatement getStatement = DataBase.getConnection().
                    prepareStatement("SELECT name FROM categories where id = " + id);
            ResultSet rs = getStatement.executeQuery();
            if (rs.next()) return rs.getString("name");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
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