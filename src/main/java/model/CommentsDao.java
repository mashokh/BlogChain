package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentsDao {
    public static ArrayList<Comments> getCommentsByBlogId(int blogId)  {
        ArrayList<Comments> res = new ArrayList<>();
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("select * from comments where blog_id = ?;");
            statement.setInt(1, blogId);

            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                res.add(new Comments(Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3)), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public static ArrayList<Comments> getCommentsByUserId(int userId) {
        ArrayList<Comments> res = new ArrayList<>();
        Connection conn = DataBase.getConnection();

        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("select * from comments where user_id = ?;");
            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();
            if(!rs.next())
                return null;
            while(rs.next()) {
                res.add(new Comments(Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3)),
                        rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public static void insertComment(Comments comment) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("INSERT INTO comments (blog_id, user_id, text, created_at) VALUES(?, ?, ?, ?);");
            statement.setInt(1, comment.getBlog_id());
            statement.setInt(2, comment.getUser_id());
            statement.setString(3, comment.getText());
            statement.setString(4, comment.getCreated_at());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
