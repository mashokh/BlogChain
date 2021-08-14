package controller;

import model.Comments;
import model.CommentsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "comments", urlPatterns = { "/viewBlog/*"})

public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user_id") == null)
            response.sendRedirect("/login");
        else
            request.getRequestDispatcher("views/viewBlog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        int userId = (Integer) request.getSession().getAttribute("user_id");
        String commentBody = request.getParameter("commentBody");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        Comments toInsert = new Comments(blogId, userId, commentBody, dateFormat.format(date), 0);
        CommentsDao.insertComment(toInsert);
        request.getRequestDispatcher("views/viewBlog.jsp").forward(request, response);
    }
}
