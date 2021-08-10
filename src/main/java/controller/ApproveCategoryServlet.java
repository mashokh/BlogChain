//Author: Giorgi Arabidze.

package controller;

import model.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ApproveCategory")
public class ApproveCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        String category = httpServletRequest.getParameter("categoryName");
        CategoryDao.changeCategoryStatus(category, true);
        httpServletRequest.setAttribute("message", "Suggested Category " + category + " Approved");
        httpServletRequest.getRequestDispatcher("views/MessageOnCommand.jsp").
                forward(httpServletRequest, httpServletResponse);
    }
}