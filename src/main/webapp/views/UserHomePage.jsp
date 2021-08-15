<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.08.2021
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>

<%
    ArrayList<Blog> usersBlogs = (ArrayList<Blog>) request.getAttribute("blogs");
    ArrayList<Blog> savedBlogs = (ArrayList<Blog>) request.getAttribute("saved-blogs");
    User homePageUser = (User) request.getAttribute("user");
    int loggedInUserId = (Integer) (request.getAttribute("loggedInUserId"));
    User loggedInUser = UserDAO.getUserById(loggedInUserId);
    int homePageUserId = (Integer) (request.getAttribute("homePageUserId"));
    ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
    boolean isLoggedInUsersPage = (loggedInUserId == homePageUserId);
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%=homePageUser.getUsername() + "'s page"%></title>
    <link rel="stylesheet" type="text/css" media="screen" href="../css/UserHomePage.css">
</head>
<body>
<div class="navigation-bar" id="navigation-bar">
    <div class="navbar-buttons">
        <a class="button" href="/logout">Logout</a>
        <%if(homePageUser.getAdmin()){%>
        <a class="button" href="/AdminHomePage">Administration</a>
        <%}%>
        <a class="button" href="/UserHomePage?userId=<%=loggedInUserId%>"><%=loggedInUser.getUsername()%></a>
        <a class="button" href="/"> Home </a>
    </div>
    <h1 style="font-size: 40px"><%=homePageUser.getUsername()%></h1>
    <img src="<%="../icons/" + homePageUser.getAvatar() + ".svg"%>" id="Avatar"/>
</div>
<div class="blog-info" id="UsersBlogs">
    <% if(isLoggedInUsersPage){%>
    <div class="blog-buttons">
        <button class="add-buttons" id="category-button">Add a Category</button>
        <button class="add-buttons" id="blog-button">Add a Blog</button>
    </div>
    <%}%>
    <%if(isLoggedInUsersPage){%>
    <div class="tab">
        <button class="tab-links" onclick="openBlogs(event, 'user-blogs')">Your Blogs</button>
        <button class="tab-links" onclick="openBlogs(event, 'saved-blogs')">Saved Blogs</button>
    </div>
    <div id="user-blogs" class="tab-content active" >
        <%
            for(Blog blog : usersBlogs){
        %>
        <p class="blog-title-and-text">
                <%if(isLoggedInUsersPage){%>
        <form class="delete-blog" action="/DeleteBlog?blogId=<%=blog.getId()%>" method="post">
            <input type="submit" name="delete" value="Delete Blog" id="delete-blog-button" onclick="return confirm('Are you sure you want to delete this blog?');"/>
        </form>
        <%}%>
        <a class="blogs" href ="/viewBlog?blogId=<%=blog.getId()%>"><%=blog.getTitle()%> </a> <br>
        <small class="created-at-and-category">
            <%=blog.getCreated_at()%> / <%=CategoryDao.getCategoryNameById(blog.getCategory_id())%><br>
        </small>
        <%=blog.getTruncatedText()%>
        </p>
        <%
            }
        %>
    </div>
    <div id="saved-blogs" class="tab-content" style="display: none">
        <%
            for(Blog blog : savedBlogs){
        %>
        <p class="blog-title-and-text">
            <a class="blogs" href ="/viewBlog?blogId=<%=blog.getId()%>"><%=blog.getTitle()%> </a> <br>
            <small class="created-at-and-category">
                <%=blog.getCreated_at()%> / <%=CategoryDao.getCategoryNameById(blog.getCategory_id())%><br>
            </small>
            <%=blog.getTruncatedText()%>
        </p>
        <%
            }
        %>
    </div>
    <%} else {%>
    <p class="blogs" style="color: #031534; font-size: 30px">
        <%=homePageUser.getUsername()%>'s Blogs
    </p>
    <%
        for(Blog blog : usersBlogs){
    %>
    <p class="blog-title-and-text">
        <a class="blogs" href ="/viewBlog?blogId=<%=blog.getId()%>"><%=blog.getTitle()%> </a> <br>
        <small class="created-at-and-category">
            <%=blog.getCreated_at()%> / <%=CategoryDao.getCategoryNameById(blog.getCategory_id())%><br>
        </small>
        <%=blog.getTruncatedText()%>
    </p>
    <%
            }
        }
    %>
</div>
        <div id="blog-modal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">&times;</span>
                    <h2>Add a Blog</h2>
                </div>
                <div class="modal-body">
                    <form method = "post" id="blog-container">
                        <div id="title-container">
                            <div class="controls">
                                <input type="text" name="blogTitle" title="title" class="BlogTitle" placeholder="Title" maxlength="16" required="" id="Title">
                            </div>
                        </div>
                        <div class="blog-category-container">
                            <label for="categories">Category:</label>
                            <select name="chosen_category" id="categories">
                                <%for(Category category : categories){%>
                                    <option value=<%=category.getName()%>><%=category.getName()%> </option>
                                <%}%>
                            </select><br>
                        </div>
                        <label for="modal-text">Blog:</label><br>
                        <textarea id="modal-text" required="" name="blogText" cols="40" rows="5" form="blog-container"></textarea><br>
                        <button class="add-blog-button" type="submit">Add</button>
                    </form>
                </div>
            </div>

        </div>
        <div id="category-modal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">&times;</span>
                    <h2>Request to Add a Category</h2>
                </div>
                <div class="modal-body">
                    <form method = "post" id="category-container">
                        <div id="category-name-container">
                            <div class="controls">
                                <div class="select-category-label">
                                    <label  for="Category">Please enter a category</label>
                                </div>
                                <div>
                                    <input type="text" name="category" title="category" class="CategoryName" placeholder="category" maxlength="16" required="" id="Category"><br>
                                </div>
                                <div class="add-category-button">
                                    <button type="submit">Add</button>
                                </div>

                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>
         <script src="../JS/UserHomePage.js"></script>
    </body>
</html>