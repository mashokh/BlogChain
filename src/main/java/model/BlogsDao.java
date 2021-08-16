package model;

import javax.persistence.PreRemove;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;


public class BlogsDao {
    public static ArrayList<Blog> getBlogsByUserId(int userId){
        ArrayList<Blog> result = new ArrayList<>();
        Connection connection = DataBase.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("select * from blogs.blogs where created_by = ?");
            statement.setString(1, String.valueOf(userId));
            ResultSet resultset = statement.executeQuery();
            while(resultset.next()){
                result.add(new Blog(resultset.getInt("id"), resultset.getString("title"), resultset.getString("text"), resultset.getInt("created_by"), resultset.getString("created_at"), resultset.getInt("category_id")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Blog> getBlogsByCategoryId(int categoryId){
        ArrayList<Blog> result = new ArrayList<>();
        Connection connection = DataBase.getConnection();
        PreparedStatement statement;
            try {
                if (categoryId != 0) {
                    statement = connection.prepareStatement("select * from blogs.blogs where category_id = ? order by created_at desc ");
                    statement.setString(1, String.valueOf(categoryId));
                } else {
                    statement = connection.prepareStatement("select * from blogs.blogs order by created_at desc ");
                }
                ResultSet resultset = statement.executeQuery();
                while (resultset.next()) {
                    result.add(new Blog(resultset.getInt("id"), resultset.getString("title"), resultset.getString("text"), resultset.getInt("created_by"), resultset.getString("created_at"), resultset.getInt("category_id")));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        return result;
    }

    public static void addBlog(String title, String text, int created_by, String created_at, int category_id){
        Connection connection = DataBase.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("insert into blogs.blogs(title, text, created_by, created_at, category_id)" +
                    "values (?,?,?,?,?)");
            statement.setString(1, title);
            statement.setString(2, text);
            statement.setInt(3, created_by);
            statement.setString(4, created_at);
            statement.setInt(5, category_id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteBlog(int id){
        Connection connection = DataBase.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("delete from blogs.blogs where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static Blog getBlogById(int id){
        Connection connection = DataBase.getConnection();
        PreparedStatement statement = null;
        Blog result = null;
        try {
            statement = connection.prepareStatement("select * from blogs.blogs where id = ?");
            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();
            resultset.next();
            if(resultset == null){
                return null;
            }
            result = new Blog(resultset.getInt("id"), resultset.getString("title"), resultset.getString("text"), resultset.getInt("created_by"), resultset.getString("created_at"), resultset.getInt("category_id"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static boolean deleteBlogByTitleAndUserId(String title, int userId){
        boolean result = false;
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete from blogs.blogs where title = ? and created_by = ?");
            statement.setString(1, title);
            statement.setInt(2, userId);
            int updatedRowCount = statement.executeUpdate();
            if(updatedRowCount > 0)
                result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
