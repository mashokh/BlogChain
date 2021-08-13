package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SavedBlogsDao {
    public static void saveBlog(int userId, int blogId){
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into blogs.saved_blogs (user_id, blog_id) values(?, ?)");
            statement.setInt(1, userId);
            statement.setInt(2, blogId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static ArrayList<Integer> usersSavedBlogsIds(int userId){
        Connection connection = DataBase.getConnection();
        ArrayList<Integer> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from blogs.saved_blogs where user_id = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                result.add(resultSet.getInt("blog_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static void deleteSavedBlogsByBlogId(int blogId){
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete from blogs.saved_blogs where blog_id = ?");
            statement.setInt(1, blogId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void deleteSavedBlogsByUserId(int userId){
        Connection connection = DataBase.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("delete from blogs.saved_blogs where user_id = ?");
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
