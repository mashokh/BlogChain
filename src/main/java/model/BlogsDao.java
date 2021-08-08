package model;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;


public class BlogsDao {
    public static ResultSet getBlogsByUserId(int userId) throws SQLException{
        ArrayList<String> result = new ArrayList<>();
        Connection connection = DataBase.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from blogs.blogs where created_by = ?");

        statement.setString(1, String.valueOf(userId));
        ResultSet resultSet = statement.executeQuery();
//        while(resultSet.next()) {
//            System.out.println("2");
//            result.add(resultSet.getString("title"));
//        }
        return resultSet;
    }

    public static ArrayList<Blogs> getBlogsByCategoryId(int categoryId) throws SQLException{
        ArrayList<Blogs> result = new ArrayList<>();
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
            result.add(new Blogs(resultset.getString("title"), resultset.getString("text"), resultset.getString("created_by"), resultset.getString("created_at"), resultset.getString("category_id")));
        }
        return result;
    }
}
