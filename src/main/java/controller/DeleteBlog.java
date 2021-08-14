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
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int blogId = Integer.parseInt(req.getParameter("blogId"));
        int userId = (Integer) req.getSession().getAttribute("user_id");
        BlogsDao.deleteBlog(blogId);
        resp.sendRedirect("/UserHomePage?userId=" + userId);
    }
}
