package controller;

import model.Comments;
import model.CommentsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "like_dislike", urlPatterns = { "/LikeDislikeServlet/*"})
public class LikeDislikeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int commentId = Integer.parseInt(request.getParameter("commentId"));
        int userId = (Integer) request.getSession().getAttribute("user_id");
        Comments comment = CommentsDao.getCommentById(commentId);
        int blogId = comment.getBlog_id();
        if(request.getParameter("like") != null) {
            CommentsDao.likeCommentLogic(commentId, userId);
        } else if (request.getParameter("dislike") != null){
            CommentsDao.dislikeCommentLogic(commentId, userId);
        }
        response.sendRedirect("/viewBlog?blogId="+blogId);
    }
}
