//Author: Giorgi Arabidze.

package controller;

import model.Category;
import model.CategoryDao;
import model.User;
import model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/AdminHomePage")
public class AdminHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        if (httpServletRequest.getSession().getAttribute("user_id") != null) {
            ArrayList<Category> suggestedCategories = CategoryDao.getCategories(false);
            ArrayList<Category> approvedCategories = CategoryDao.getCategories(true);
            ArrayList<User> admins = UserDAO.getUserByAdmin(true);
            httpServletRequest.setAttribute("suggestedCategories", suggestedCategories);
            httpServletRequest.setAttribute("approvedCategories", approvedCategories);
            httpServletRequest.setAttribute("admins", admins);
            httpServletRequest.getRequestDispatcher("views/AdminPage.jsp").
                    forward(httpServletRequest, httpServletResponse);
        } else
            httpServletResponse.sendRedirect("login");
    }
}