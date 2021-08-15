//Author: Giorgi Arabidze.

package controller;

import model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/AdminUserDeletion")
public class AdminDeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        String username = httpServletRequest.getParameter("username");
        if (UserDAO.userIsAdmin(username)){
            httpServletRequest.setAttribute("message", "User With Name " + username + " Is Admin");
        }else {
            int id = UserDAO.getIdByUsername(username);
            boolean deletionDone = UserDAO.deleteUser(id);
            if (deletionDone){
                httpServletRequest.setAttribute("message", "User With Name " + username + " Deleted");
            }else{
                httpServletRequest.setAttribute("message", "User With Name " + username + " Does not Exist");
            }
        }
        httpServletRequest.getRequestDispatcher("views/MessageOnCommand.jsp").
                forward(httpServletRequest, httpServletResponse);
    }
}
