<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<!DOCTYPE html>

<%
    int userId = 0;
    User user = null;
    if (session.getAttribute("user_id") != null) {
        userId = (Integer) session.getAttribute("user_id");
        user = UserDAO.getUserById(userId);
    }

    int categoryId = 0;
    if (request.getParameter("categoryId") != null)
        categoryId = Integer.parseInt(request.getParameter("categoryId"));

    ArrayList<Blog> blogs = BlogsDao.getBlogsByCategoryId(categoryId);
    ArrayList<Category> categories = CategoryDao.getCategories(true);
%>

<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/index.css">
    <title>BlogChain</title>
</head>

<body>

    <div class="navbar-container">
        <div class="navbar-category-label">Categories</div>
        <% if (userId == 0) { %>
        <a href="/register">Register</a>
        <a href="/login">Login</a>
        <% } else { %>
        <a href="/logout">Logout</a>
        <a href="/UserHomePage/?userId=<%=userId%>" > <%= user.getUsername()%> </a>
        <img src="icons/<%=user.getAvatar()%>.svg" alt="profile">
        <% } %>
    </div>

    <div class="blogs-categories-sidebar">
        <% for (Category category : categories) { %>
            <div class="sidebar-category">
                <a href="/?categoryId=<%=category.getId()%>"><%=category.getName()%></a>
            </div>
        <% } %>
    </div>

    <div class="blogs-container">
        <h1 class="blogs-title">Blogs</h1>
        <br>
        <div class="blogs-list">
            <% for (Blog blog : blogs) { %>
                <div class="blog-container">

                    <div class="blog-title-and-category">
                        <span class="blog-title"> <%= blog.getTitle() %> </span>
                        <div class="blog-category right-info"> <%=  blog.getCategory_id() %> </div>
                    </div>
                    <small class="blog-created-at right-info" id="blog-date-and-author">
                        <%= blog.getCreated_at() %>
                        By:<a href="/UserHomePage/?userId=<%=blog.getCreated_by()%>"><%=  blog.getCreated_by() %> </a>
                    </small>

                    <div class="blog-body "> <%= blog.getText() %> </div>
                </div>
            <% } %>
        </div>
    </div>

</body>

</html>