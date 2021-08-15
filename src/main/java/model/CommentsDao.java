package model;

import java.sql.*;
import java.util.ArrayList;



public class CommentsDao {
    /**
     * Method takes blog id and returns all comments for given blog
     * @param blogId
     * @return ArrayList<Comments>
     */
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

    /**
     * Method takes comment id and returns comment associated with the id
     * @param commentId
     * @return Comment
     */
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

    /**
     * Method takes comment and places it in database returns comment id
     * @param comment
     * @return int
     */
    public static int insertComment(Comments comment) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("INSERT INTO comments (blog_id, user_id, text, created_at, num_likes) VALUES(?, ?, ?, ?, ?);"
                    ,  Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, comment.getBlog_id());
            statement.setInt(2, comment.getUser_id());
            statement.setString(3, comment.getText());
            statement.setString(4, comment.getCreated_at());
            statement.setInt(5, 0);
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();

            if (keys.next()) return keys.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    /**
     * Method deletes comment with associated id
     * @param comment_id
     */
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

    /**
     * Methods calculates likes and dislike associated with the comment and returns their sum
     * @param comment_id
     * @return int
     */
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

    /**
     * Methods checks if user with user_id has liked comment with comment_id
     * @param comment_id
     * @param user_id
     * @return boolean
     */
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

    /**
     * Methods checks if user with user_id has disliked comment with comment_id
     * @param comment_id
     * @param user_id
     * @return
     */
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

    /**
     * Methods removes like from comment with comment_id and user with user_id
     * @param comment_id
     * @param user_id
     */
    public static void removeLike(int comment_id, int user_id) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("DELETE FROM comment_reactions WHERE comment_id = ? AND user_id = ? AND reaction = ?");
            statement.setInt(1, comment_id);
            statement.setInt(2, user_id);
            statement.setString(3, "LIKE");
            statement.execute();
            updateCommentRating(comment_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Methods removes dislike from comment with comment_id and user with user_id
     * @param comment_id
     * @param user_id
     */
    public static void removeDislike(int comment_id, int user_id) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("DELETE FROM comment_reactions WHERE comment_id = ? AND user_id = ? AND reaction = ?");
            statement.setInt(1, comment_id);
            statement.setInt(2, user_id);
            statement.setString(3, "DISLIKE");
            statement.execute();
            updateCommentRating(comment_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Methods adds like from comment with comment_id and user with user_id
     * @param comment_id
     * @param user_id
     */
    public static void likeComment(int comment_id, int user_id) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("INSERT INTO comment_reactions (comment_id, user_id, reaction) VALUES (?, ?, ?)");
            statement.setInt(1, comment_id);
            statement.setInt(2, user_id);
            statement.setString(3, "LIKE");
            statement.execute();
            updateCommentRating(comment_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Methods adds dislike from comment with comment_id and user with user_id
     * @param comment_id
     * @param user_id
     */
    public static void dislikeComment(int comment_id, int user_id) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("INSERT INTO comment_reactions (comment_id, user_id, reaction) VALUES (?, ?, ?)");
            statement.setInt(1, comment_id);
            statement.setInt(2, user_id);
            statement.setString(3, "DISLIKE");
            statement.execute();
            updateCommentRating(comment_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method updates comments table
     * @param comment_id
     */
    public static void updateCommentRating(int comment_id) {
        Connection conn = DataBase.getConnection();
        PreparedStatement statement;
        try {
            int numLikes = getCommentRate(comment_id);
            statement = conn.prepareStatement("UPDATE comments SET num_likes = ? WHERE id = ?");
            statement.setInt(1, numLikes);
            statement.setInt(2, comment_id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method checks for likes and dislikes and updates comments table accordingly
     * when user likes a comment
     * @param comment_id
     * @param user_id
     */
    public static void likeCommentLogic(int comment_id, int user_id) {
        if(CommentsDao.userDisliked(comment_id, user_id)) {
            CommentsDao.removeDislike(comment_id,user_id);
            CommentsDao.likeComment(comment_id, user_id);
        }
        else if(!CommentsDao.userLiked(comment_id, user_id)) {
            CommentsDao.likeComment(comment_id, user_id);
        } else if(CommentsDao.userLiked(comment_id, user_id)) {
            CommentsDao.removeLike(comment_id,user_id);
        }
    }

    /**
     * Method checks for likes and dislikes and updates comments table accordingly
     * when user dislikes a comment
     * @param comment_id
     * @param user_id
     */
    public static void dislikeCommentLogic(int comment_id, int user_id) {
        if(CommentsDao.userLiked(comment_id, user_id)) {
            CommentsDao.removeLike(comment_id,user_id);
            CommentsDao.dislikeComment(comment_id, user_id);
        } else if(!CommentsDao.userDisliked(comment_id, user_id)) {
            CommentsDao.dislikeComment(comment_id, user_id);
        } else if(CommentsDao.userDisliked(comment_id, user_id)) {
            CommentsDao.removeDislike(comment_id, user_id);
        }
    }
}
