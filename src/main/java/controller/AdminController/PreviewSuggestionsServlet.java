package controller.AdminController;


import model.CategoryDao;
import model.CategoryData;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/PreviewSuggestions")
public class PreviewSuggestionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ArrayList<CategoryDao> suggestedCategories = CategoryData.getUnapprovedCategories();
        httpServletRequest.setAttribute("categories", suggestedCategories);
        httpServletRequest.getRequestDispatcher("WEB-INF/suggestionsPage.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}
