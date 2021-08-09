package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentsDao {
    public static ArrayList<Comments> getCommentsByBlogId(int blogId) throws SQLException {
        ArrayList<Comments> res = new ArrayList<>();
        Connection conn = DataBase.getConnection();
        PreparedStatement statement = conn.prepareStatement("select * from comments where blog_id = ?;");
        statement.setInt(1, blogId);

        ResultSet rs = statement.executeQuery();
        if(!rs.next())
            return null;
        while(rs.next()) {
            res.add(new Comments(rs.getString(0), rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        return res;
    }

    public static ArrayList<Comments> getCommentsByUserId(int userId) throws SQLException {
        ArrayList<Comments> res = new ArrayList<>();
        Connection conn = DataBase.getConnection();
        PreparedStatement statement = conn.prepareStatement("select * from comments where user_id = ?;");
        statement.setInt(1, userId);

        ResultSet rs = statement.executeQuery();
        if(!rs.next())
            return null;
        while(rs.next()) {
            res.add(new Comments(rs.getString(0), rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        return res;
    }

    public static void insertComment(int userId, int blogId, String date, String text) throws SQLException {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO comments (blog_id, user_id, text, created_at) VALUES(?, ?, ?, ?);");
        statement.setInt(1, blogId);
        statement.setInt(2, userId);
        statement.setString(3, text);
        statement.setString(4, date);
        statement.execute();
    }
}
