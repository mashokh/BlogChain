package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDao {
    private static Connection connection;

    public CategoryDao(){
        this.connection = DataBase.getConnection();
    }

    public static boolean suggestCategory(String name){
        try {
            PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM categories WHERE name = \"" + name + "\"");
            ResultSet rs = checkStatement.executeQuery();
            if(rs.next()) return false;
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO categories(name,is_approved) values(\"" + name + "\", false)");
            insertStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<Category> getCategories(boolean status){
        ArrayList<Category> categories = new ArrayList<>();
        try {
            PreparedStatement getStatement = connection.prepareStatement("SELECT * FROM categories WHERE is_approved is \"" + status + "\"");
            ResultSet rs = getStatement.executeQuery();
            generateCategoryList(rs, categories);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }


    public static void changeCategoryStatus(String name, boolean status){
        try {
            PreparedStatement approval = connection.prepareStatement("UPDATE categories SET is_approved = \"" + status + "\" WHERE name = \"" + name + "\"");
            approval.executeUpdate();
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

