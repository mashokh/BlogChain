package model;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;


public class Blogs {
    public static ResultSet getBlogsByUserId(int userId) throws SQLException{
        System.out.println("1");
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
}
