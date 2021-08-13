package controller;

import model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user_id") == null)
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        else
            response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = String.valueOf(request.getParameter("password").hashCode());
        if (UserDAO.successLogin(username, password)) {
            request.getSession().setAttribute("user_id", UserDAO.getIdByUsername(username));
            response.sendRedirect("/");
        } else {
            request.setAttribute("error", "Username or password is incorrect");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }

    }
}
