<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %><%--
  Created by IntelliJ IDEA.
  User: lukaa
  Date: 09-Aug-21
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
    int blogId = Integer.parseInt(request.getParameter("blogId"));
    User user = (User) session.getAttribute("user");
    Blog blog = BlogsDao.getBlogById(blogId);
    ArrayList<Comments> comments = CommentsDao.getCommentsByBlogId(blogId);
    User author = UserDAO.getUserById(Integer.parseInt(blog.getCreated_by()));
    String authorAvatar ="../icons/" + author.getAvatar() + ".svg";
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="../css/viewBlog.css">
    <title>
        <%
            out.println("Viewing blog: " + blog.getTitle());
        %>
    </title>
</head>
<body>

<p id = "blog_body">
    <% out.println(blog.getText()); %>
</p>
<li id = "blog_info">
    <i>
        <img src = "<% out.println(authorAvatar); %>">
    </i>
    <i id = "blog_author">
        <% out.println(author.getUsername());%>
    </i>

    <i id = "date_created">
        <% out.println(blog.getCreated_at());%>
    </i>
</li>

<div class="separator">Comments</div>

<form id = "add_comment" method="post">
    <div>
        <label>Enter your comment text: </label>
    </div>
    <div>
        <input type="text" name = "commentBody" placeholder="Enter your comment" size = "50" required=""/>
        <input type = "submit" value = "Add Comment">
    </div>
</form>

<%
    for (Comments comment : comments) {
        User currUser = UserDAO.getUserById(comment.getUser_id());
        String avatar = "../icons/" + currUser.getAvatar() + ".svg";
%>

<div id = "comment">
    <ul id = "user_comment" class = "vl">
            <div id = "comment_body">
                <p>
                    <img id = "comment_avatar" src = "<%out.println(avatar);%>">
                    <% out.println(comment.getText()); %>
                </p>
            </div>
            <div id = "comment_info">
                <li>
                    <i id = "user_name"></i>
                    <% out.println("By: " + currUser.getUsername()); %>
                    <i id = "comment_calendar"></i>
                    <% out.println("On: " + comment.getCreated_at()); %>
                </li>
            </div>
    </ul>
</div>

<%
    }
%>
</body>
</html>
