package controller;

import model.Blogs;
import model.BlogsDao;
import model.User;
import model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "UserHomePage", urlPatterns = { "/UserHomePage/*"})

public class HomePageServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        System.out.println(userId);
        HttpSession session = req.getSession();
        int loggedInUserId = -1;
        if(session.getAttribute(("user_id")) != null){
            loggedInUserId = (Integer) session.getAttribute("user_id");
        }
        try {
            User user = UserDAO.getUserById(userId);
            ResultSet blogs = BlogsDao.getBlogsByUserId(userId);
            session.setAttribute("blogs", blogs);
            session.setAttribute("loggedInUserId", loggedInUserId);
            session.setAttribute("homePageUserId", userId);
            session.setAttribute("user", user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.getRequestDispatcher("/views/UserHomePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
        String title = req.getParameter("blogTitle");
        String category = req.getParameter("categories");
        String text = req.getParameter("blogText");
        System.out.println(title);
    }
}
