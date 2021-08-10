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
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="../css/viewBlog.css">
    <title>
        <%
            out.println("Viewing blog: " + blogId);
        %>
    </title>
</head>
<body>

<p>
    <% out.println(blog.getText()); %>
</p>


<%
    for (Comments comment : comments) {
        User currUser = UserDAO.getUserById(comment.getUser_id());
        String avatar = "../icons/" + currUser.getAvatar() + ".svg";
%>

<div class = "comment">
    <ul class = "user_comment">
        <div class = "user_avatar">
            <img src = "<%out.println(avatar);%>">
            <div class = "comment_body">
                <p>
                    <% out.println(comment.getText()); %>
                </p>
            </div>
            <div class = "comment_info">
                <li>
                    <i class = "comment_calendar"></i>
                    <% out.println(comment.getCreated_at()); %>
                    <i class = "user_name"></i>
                    <%
                        out.println(currUser.getUsername());
                    %>
                </li>
            </div>
        </div>
    </ul>
</div>

<%
    }
%>
<form method="post">
    <label>Enter your comment text: </label>
    <input type="text" name = "commentBody"/>
    <input type = "submit" value = "Add Comment">
</form>
</body>
</html>
