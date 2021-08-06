package model;

import java.sql.*;
import java.util.ArrayList;

public class CategoryData {
    private static Connection connection;

    public CategoryData(){
        this.connection = DataBase.getConnection();
    }

    public static boolean suggestCategory(String name){
        try {
            PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM CATEGORIES WHERE name = \"" + name + "\"");
            ResultSet rs = checkStatement.executeQuery();
            if(rs.next()) return false;
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO categories(name,is_approved) values(" + name + ", false)");
            insertStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<CategoryDao> getApprovedCategories(){
        ArrayList<CategoryDao> categories = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CATEGORIES WHERE is_approved is TRUE");
            ResultSet rs = ps.executeQuery();
            generateCategoryList(rs, categories);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }

    public static ArrayList<CategoryDao> getUnapprovedCategories(){
        ArrayList<CategoryDao> categories = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CATEGORIES WHERE is_approved is FALSE");
            ResultSet rs = ps.executeQuery();
            generateCategoryList(rs, categories);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }

    private static void generateCategoryList(ResultSet rs, ArrayList<CategoryDao> categories){
        try {
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                boolean isApproved = rs.getBoolean("is_approved");
                CategoryDao curr = new CategoryDao(id, name, isApproved);
                categories.add(curr);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

}
