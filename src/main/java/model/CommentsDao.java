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
                Comments comment = new Comments(Integer.parseInt(rs.getString(2)),
                        Integer.parseInt(rs.getString(3)), rs.getString(4),
                            rs.getString(5), Integer.parseInt(rs.getString(6)));
                comment.setComment_id(Integer.parseInt(rs.getString(1)));
                res.add(comment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public static Comments getCommentById(int commentId) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        Comments comment = null;
        try {
            statement = conn.prepareStatement("select * from comments where id = ?;");
            statement.setInt(1, commentId);

            ResultSet rs = statement.executeQuery();
            if(!rs.next())
                return null;
            comment = new Comments(Integer.parseInt(rs.getString(2)),
                    Integer.parseInt(rs.getString(3)), rs.getString(4),
                        rs.getString(5), Integer.parseInt(rs.getString(6)));
            comment.setComment_id(Integer.parseInt(rs.getString(1)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comment;
    }

    public static void insertComment(Comments comment) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("INSERT INTO comments (blog_id, user_id, text, created_at, num_likes) VALUES(?, ?, ?, ?, ?);");
            statement.setInt(1, comment.getBlog_id());
            statement.setInt(2, comment.getUser_id());
            statement.setString(3, comment.getText());
            statement.setString(4, comment.getCreated_at());
            statement.setInt(5, 0);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteCommentByCommentId(int comment_id) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("DELETE FROM comments WHERE id = ?");
            statement.setInt(1, comment_id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteCommentsByUserId(int user_id) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("DELETE FROM comments WHERE user_id = ?");
            statement.setInt(1, user_id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteCommentsByBlogId(int blog_id) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("DELETE FROM comments WHERE blog_id = ?");
            statement.setInt(1, blog_id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int getCommentRate(int comment_id) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        int rate = 0;
        try {
            statement = conn.prepareStatement("SELECT COUNT(*) FROM comment_reactions WHERE comment_id = ? AND reaction = ?");
            statement.setInt(1, comment_id);
            statement.setString(2, "LIKE");
            ResultSet rs = statement.executeQuery();
            int numLikes = 0;
            if(rs.next())
                numLikes = rs.getInt(1);
            statement = conn.prepareStatement("SELECT COUNT(*) FROM comment_reactions WHERE comment_id = ? AND reaction = ?");
            statement.setInt(1, comment_id);
            statement.setString(2, "DISLIKE");
            rs = statement.executeQuery();
            int numDislikes = 0;
            if(rs.next())
                numDislikes = rs.getInt(1);
            rate = numLikes-numDislikes;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rate;
    }

    public static boolean userLiked(int comment_id, int user_id) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        boolean contains = false;
        try {
            statement = conn.prepareStatement("SELECT COUNT(*) FROM comment_reactions WHERE user_id = ? AND comment_id = ? AND reaction = ?");
            statement.setInt(1, user_id);
            statement.setInt(2, comment_id);
            statement.setString(3, "LIKE");
            ResultSet rs = statement.executeQuery();
            int liked = 0;
            if(rs.next())
                liked = rs.getInt(1);
            if(liked > 0)
                contains = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contains;
    }

    public static boolean userDisliked(int comment_id, int user_id) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        boolean contains = false;
        try {
            statement = conn.prepareStatement("SELECT COUNT(*) FROM comment_reactions WHERE user_id = ? AND comment_id = ? AND reaction = ?");
            statement.setInt(1, user_id);
            statement.setInt(2, comment_id);
            statement.setString(3, "DISLIKE");
            ResultSet rs = statement.executeQuery();
            int liked = 0;
            if(rs.next())
                liked = rs.getInt(1);
            if(liked > 0)
                contains = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contains;
    }

    public static void likeComment(int comment_id, int user_id) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("INSERT INTO comment_reactions (comment_id, user_id, reaction) VALUES (?, ?, ?)");
            statement.setInt(1, comment_id);
            statement.setInt(2, user_id);
            statement.setString(3, "LIKE");
            int numLikes = getCommentRate(comment_id);
            statement = conn.prepareStatement("UPDATE comments SET num_likes = ? WHERE id = ?");
            statement.setInt(1, numLikes);
            statement.setInt(2, comment_id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void dislikeComment(int comment_id, int user_id) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("INSERT INTO comment_reactions (comment_id, user_id, reaction) VALUES (?, ?, ?)");
            statement.setInt(1, comment_id);
            statement.setInt(2, user_id);
            statement.setString(3, "DISLIKE");
            int numLikes = getCommentRate(comment_id);
            statement = conn.prepareStatement("UPDATE comments SET num_likes = ? WHERE id = ?");
            statement.setInt(1, numLikes);
            statement.setInt(2, comment_id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
