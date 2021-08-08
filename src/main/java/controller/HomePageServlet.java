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
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            ArrayList<Blogs> blogs = BlogsDao.getBlogsByUserId(userId);
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
//        String category = req.getParameter("categories");
        String text = req.getParameter("blogText");
        if(text != null) {
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            int categoryId = 1;
            String formatedDate = date.format(formatter);
            int created_by = (int) req.getSession().getAttribute("user_id");
            try {
                BlogsDao.addBlog(title, text, created_by, formatedDate, categoryId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else{

        }
    }
}
