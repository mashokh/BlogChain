package controller;

import model.Blogs;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/HomePage")

public class HomePageServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       HttpSession session = req.getSession();
       System.out.println("shdjdh");
//        session.getAttribute("user");
        User user = new User(1, "masho", "m", "src/main/webapp/icons/black.svg", false);
        int userId = user.getId();
        String name  = user.getName();
        System.out.println(name);
        session.setAttribute("name", name);
        try {
            ArrayList<String> blogs = Blogs.getBlogsByUserId(userId);
            System.out.print(blogs);
            session.setAttribute("blogs", blogs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/HomePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
