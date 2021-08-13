package controller;

import model.Comments;
import model.CommentsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "delete_comments", urlPatterns = { "/DeleteCommentServlet/*"})
public class DeleteCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int commentId = Integer.parseInt(request.getParameter("commentId"));
        int userId = (Integer) request.getSession().getAttribute("user_id");
        Comments comment = CommentsDao.getCommentById(commentId);
        int blogId = comment.getBlog_id();
        if(userId == comment.getUser_id()) {
            CommentsDao.deleteCommentByCommentId(commentId);
        }
        response.sendRedirect("/viewBlog?blogId="+blogId);
    }
}
