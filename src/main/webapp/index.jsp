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

    <div class="blogs-categories-sidebar">
        <div class="blogs-categories-label">Categories</div>
        <% for (Category category : categories) { %>
        <div class="sidebar-category">
            <a href="/?categoryId=<%=category.getId()%>"><%=category.getName()%></a>
        </div>
        <% } %>
    </div>

    <div class="navbar-container">
        <div class="navbar-label">BlogChain</div>
        <% if (userId == 0) { %>
        <a class="navbar-right" href="/register">Register</a>
        <a class="navbar-right" href="/login">Login</a>
        <% } else { %>
        <a class="navbar-right" href="/logout">Logout</a>
        <a class="navbar-right" href="/UserHomePage/?userId=<%=userId%>" > <%= user.getUsername()%> </a>
        <a href="/UserHomePage/?userId=<%=userId%>">
            <img class="navbar-right" src="icons/<%=user.getAvatar()%>.svg" alt="profile">
        </a>
        <% } %>
    </div>



    <div class="blogs-container">
        <h1 class="blogs-title">Blogs</h1>
        <br>
        <div class="blogs-list">
            <% for (Blog blog : blogs) { %>
                <% User author = UserDAO.getUserById(blog.getCreated_by()); %>
                <div class="blog-container">

                    <div class="blog-title-and-category">
                        <a href ="/viewBlog?blogId=<%=blog.getId()%>">
                            <span class="blog-title"> <%= blog.getTitle() %> </span>
                        </a>
                        <div class="blog-category right-info"> <%=  CategoryDao.getCategoryNameById(blog.getCategory_id()) %> </div>
                    </div>
                    <small class="blog-date-author right-info" id="blog-date-and-author">
                        <span class="blog-created-at"><%= blog.getCreated_at() %></span>
                        <a href="/UserHomePage/?userId=<%=blog.getCreated_by()%>">
                            <img class="blog-author-avatar" src="icons/<%=author.getAvatar()%>.svg"  alt="avatar">
                        </a>
                        <a class="blog-author-username" href="/UserHomePage/?userId=<%=blog.getCreated_by()%>"><%=  author.getUsername() %> </a>
                    </small>
                    <div class="blog-body "> <%= blog.getTruncatedText() %> </div>
                </div>
            <% } %>
        </div>
    </div>

</body>

</html>