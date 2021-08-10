//Author: Giorgi Arabidze.

package controller;

import model.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/AddCategory")
public class AddCategoryManuallyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        String category = httpServletRequest.getParameter("addCategoryName");
        if (CategoryDao.categoryExists(category)){
            httpServletRequest.setAttribute("message", category + " is Already Suggested or Added");
        }else {
            CategoryDao.suggestCategory(category);
            CategoryDao.changeCategoryStatus(category, true);
            httpServletRequest.setAttribute("message", category + " Successfully Added in Categories");
        }
        httpServletRequest.getRequestDispatcher("views/MessageOnCommand.jsp").
                forward(httpServletRequest, httpServletResponse);
    }
}
