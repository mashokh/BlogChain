package controller.AdminController;

import model.CategoryData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/DenyCategory")
public class DenyCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        CategoryData.deleteCategory(httpServletRequest.getParameter("categoryName"));
        httpServletResponse.sendRedirect("/PreviewSuggestions");
        CategoryData.getApprovedCategories();
    }
}
