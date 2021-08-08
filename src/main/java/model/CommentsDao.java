package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentsDao {
    public static ResultSet getCommentsByBlogId(int userId) throws SQLException {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement = conn.prepareStatement("select * from comments where blog_id = ?;");
        statement.setInt(1, userId);

        ResultSet rs = statement.executeQuery();
        if(!rs.next())
            return null;
        return rs;
    }
}
