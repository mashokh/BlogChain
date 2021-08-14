//Author: Giorgi Arabidze.

package controller;

import model.BlogsDao;
import model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/AdminBlogDeletion")
public class AdminDeleteBlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        String title = httpServletRequest.getParameter("blogTitle");
        String username = httpServletRequest.getParameter("authorName");
        int id = UserDAO.getIdByUsername(username);
        boolean deletionDone = BlogsDao.deleteBlogByTitleAndUserId(title, id);
        if(deletionDone){
            httpServletRequest.setAttribute("message", "Blog With Title " + title + " and Author " + username + " Deleted");
        }else {
            httpServletRequest.setAttribute("message", "Blog With Title " + title + " and Author " + username + " Does Not Exist");
        }
        httpServletRequest.getRequestDispatcher("views/MessageOnCommand.jsp").
                forward(httpServletRequest, httpServletResponse);
    }
}