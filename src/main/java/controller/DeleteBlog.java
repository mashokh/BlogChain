package controller;

import model.BlogsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteBlog", urlPatterns = { "/DeleteBlog/*"})

public class DeleteBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String blogTitle = req.getParameter("blogTitle");
        int userId = (Integer) req.getSession().getAttribute("user_id");
        try {
            BlogsDao.deleteBlog(blogTitle);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("/UserHomePage?userId=" + userId);
    }
}
