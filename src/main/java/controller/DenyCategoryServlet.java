//Author: Giorgi Arabidze.

package controller;

import model.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/DenyCategory")
public class DenyCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        String category = httpServletRequest.getParameter("categoryName");
        CategoryDao.deleteCategory(category);
        httpServletRequest.setAttribute("message", "Suggested Category " + category + " Denied");
        httpServletRequest.getRequestDispatcher("views/MessageOnCommand.jsp").
                forward(httpServletRequest, httpServletResponse);
    }
}