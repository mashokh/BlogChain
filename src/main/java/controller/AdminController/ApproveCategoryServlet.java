package controller.AdminController;

import model.CategoryData;

import javax.enterprise.context.spi.Context;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ApproveCategory")
public class ApproveCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        CategoryData.approveCategory(httpServletRequest.getParameter("approvalButton"));
        httpServletResponse.sendRedirect("/PreviewSuggestions");
    }
}
