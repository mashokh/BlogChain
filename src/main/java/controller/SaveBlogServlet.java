package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "save_blog", urlPatterns = { "/SaveBlogServlet/*"})
public class SaveBlogServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        int userId = (Integer) request.getSession().getAttribute("user_id");
        //TODO add blog to saved_blogs table with user_id masho
        request.getRequestDispatcher("views/viewBlog.jsp").forward(request, response);
    }
}
