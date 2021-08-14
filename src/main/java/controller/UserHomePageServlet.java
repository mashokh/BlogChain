package controller;

import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet(name = "UserHomePage", urlPatterns = { "/UserHomePage/*"})

public class UserHomePageServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        HttpSession session = req.getSession();
        int loggedInUserId = -1;
        if (session.getAttribute(("user_id")) != null) {
            loggedInUserId = (Integer) session.getAttribute("user_id");
            User user = UserDAO.getUserById(userId);
            ArrayList<Blog> blogs = BlogsDao.getBlogsByUserId(userId);
            ArrayList<Category> categories = CategoryDao.getCategories(true);
            ArrayList<Integer> savedBlogsIds = SavedBlogsDao.usersSavedBlogsIds(loggedInUserId);
            ArrayList<Blog> savedBlogs = new ArrayList<>();
            for(int id:savedBlogsIds){
                savedBlogs.add(BlogsDao.getBlogById(id));
            }
            req.setAttribute("saved-blogs", savedBlogs);
            req.setAttribute("blogs", blogs);
            req.setAttribute("loggedInUserId", loggedInUserId);
            req.setAttribute("homePageUserId", userId);
            req.setAttribute("user", user);
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/views/UserHomePage.jsp").forward(req, resp);
        } else{
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("blogTitle");
        String category = req.getParameter("chosen_category");
        String text = req.getParameter("blogText");
        if(text != null) {
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            int categoryId = CategoryDao.getCategoryIdByName(category);

            String formatedDate = date.format(formatter);
            int created_by = (int) req.getSession().getAttribute("user_id");
            BlogsDao.addBlog(title, text, created_by, formatedDate, categoryId);
        } else{
            String suggestedCategory = req.getParameter("category");
            CategoryDao.suggestCategory(suggestedCategory);
        }
        resp.sendRedirect(String.valueOf(req.getRequestURL()) + "?" + req.getQueryString());
    }
}
