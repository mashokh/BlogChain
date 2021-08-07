package controller;

import com.mysql.cj.protocol.Resultset;
import model.Blogs;
import model.User;

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

@WebServlet("/UserHomePage/1")

public class HomePageServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getPathInfo();
        System.out.println(param);
        HttpSession session = req.getSession();
        //        User user = session.getAttribute("user");
        User user = new User(1, "masho", "m", "icons/blue.svg", false);
        int userId = user.getId();
        session.setAttribute("user", user);
        try {
            ResultSet blogs = Blogs.getBlogsByUserId(userId);
            session.setAttribute("blogs", blogs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/UserHomePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
