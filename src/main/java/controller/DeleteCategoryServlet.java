//Author: Giorgi Arabidze.

package controller;

import model.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/DeleteCategory")
public class DeleteCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        String category = httpServletRequest.getParameter("deleteCategoryName");
        System.out.println(category);
        if (!CategoryDao.categoryExists(category)){
            httpServletRequest.setAttribute("message", category + " Does not exist in Categories");
        }else {
            CategoryDao.deleteCategory(category);
            httpServletRequest.setAttribute("message", category + " Deleted Successfully from Categories");
        }
        httpServletRequest.getRequestDispatcher("views/MessageOnCommand.jsp").
                forward(httpServletRequest, httpServletResponse);
    }
}
