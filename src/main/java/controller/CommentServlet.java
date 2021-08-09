package controller;

import model.Comments;
import model.CommentsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "comments", urlPatterns = { "/viewBlog/*"})

public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("views/viewBlog.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        int blogId = Integer.parseInt(httpServletRequest.getParameter("blogId"));
        String commentBody = httpServletRequest.getParameter("commentBody");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        Comments toInsert = new Comments(blogId, 1, commentBody, dateFormat.format(date));
        CommentsDao.insertComment(toInsert);
        httpServletRequest.getRequestDispatcher("views/viewBlog.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
