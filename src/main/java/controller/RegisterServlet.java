package controller;

import model.User;
import model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user_id") == null)
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
        else
            response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User newUser = loadUser(request);

        if (newUser == null) {
            request.setAttribute("error", "Passwords must be the same");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
        } else {

            if (UserDAO.usernameExists(newUser.getUsername())) {
                request.setAttribute("error", "Username already exists");
                request.getRequestDispatcher("views/register.jsp").forward(request, response);
            } else {
                UserDAO.addUser(newUser);
                request.getSession().setAttribute("user_id", UserDAO.getIdByUsername(newUser.getUsername()));
                response.sendRedirect("/");
            }
        }
    }

    /** Method loads a form to User.
     * @param request HttpServletRequest
     *
     * @return null if password and password-repeat do not match. Otherwise - User */
    private User loadUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = String.valueOf(request.getParameter("password").hashCode());
        String passwordRepeat = String.valueOf(request.getParameter("password-repeat").hashCode());
        String avatar = request.getParameter("avatar");

        if (!password.equals(passwordRepeat)) return null;

        return new User(0, username, password, avatar, false);
    }
}
