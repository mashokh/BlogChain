package model;

import java.sql.*;
import java.util.ArrayList;


public class BlogsDao {
    public static ArrayList<Blog> getBlogsByUserId(int userId) throws SQLException{
        ArrayList<Blog> result = new ArrayList<>();
        Connection connection = DataBase.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from blogs.blogs where created_by = ?");

        statement.setString(1, String.valueOf(userId));
        ResultSet resultset = statement.executeQuery();
       while(resultset.next()){
            result.add(new Blog(resultset.getString("title"), resultset.getString("text"), resultset.getString("created_by"), resultset.getString("created_at"), resultset.getString("category_id")));
        }
        return result;
    }

    public static ArrayList<Blog> getBlogsByCategoryId(int categoryId) throws SQLException{
        ArrayList<Blog> result = new ArrayList<>();
        Connection connection = DataBase.getConnection();
        PreparedStatement statement;
        ResultSet resultset;
        if(categoryId != 0) {
            statement = connection.prepareStatement("select * from blogs.blogs where category_id = ?");
            statement.setString(1, String.valueOf(categoryId));
            resultset = statement.executeQuery();
        } else{
            statement = connection.prepareStatement("select * from blogs.blogs");
            resultset = statement.executeQuery();
        }
        while(resultset.next()){
            result.add(new Blog(resultset.getString("title"), resultset.getString("text"), resultset.getString("created_by"), resultset.getString("created_at"), resultset.getString("category_id")));
        }
        return result;
    }

    public static void addBlog(String title, String text, int created_by, String created_at, int category_id) throws SQLException {
        Connection connection = DataBase.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into blogs.blogs(title, text, created_by, created_at, category_id)" +
                "values (?,?,?,?,?)");
        statement.setString(1, title);
        statement.setString(2, text);
        statement.setInt(3, created_by);
        statement.setString(4, created_at);
        statement.setInt(5, category_id);
        statement.executeUpdate();
    }

    public static void deleteBlog(String title) throws SQLException {
        Connection connection = DataBase.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from blogs.blogs where title = ?");
        statement.setString(1, title);
        statement.executeUpdate();
    }

    public static int getIdByTitle(String title) throws SQLException{
        Connection connection = DataBase.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from blogs.blogs where title = ?");
        statement.setString(1, title);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int id = resultSet.getInt("id");
        return id;
    }

    public static Blog getBlogById(int id) throws SQLException {
        Connection connection = DataBase.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from blogs.blogs where id = ?");
        statement.setInt(1, id);
        ResultSet resultset = statement.executeQuery();
        resultset.next();
        return new Blog(resultset.getString("title"), resultset.getString("text"), resultset.getString("created_by"), resultset.getString("created_at"), resultset.getString("category_id"));
    }
}
