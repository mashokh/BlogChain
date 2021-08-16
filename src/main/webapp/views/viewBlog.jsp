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
    int loggedInUserId = (Integer) session.getAttribute("user_id");
    int blogId = Integer.parseInt(request.getParameter("blogId"));
    Blog blog = BlogsDao.getBlogById(blogId);
    ArrayList<Comments> comments = CommentsDao.getCommentsByBlogId(blogId);
    User author = UserDAO.getUserById(blog.getCreated_by());
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

<div class = "navbar-style">
    <b>
        <% out.println(blog.getTitle()); %>
    </b>
    <a href = "/logout">Logout</a>
    <a href = "/">Home</a>
</div>

<p id = "blog_body">
    <% out.println(blog.getText()); %>
</p>
<li id = "blog_info">
    <i>
        <% out.println("<a href = UserHomePage/?userId=" + author.getId()+ ">"
                +"<img src = "+ authorAvatar + ">" + "</a>"); %>

    </i>
    <i id = "blog_author">
        <% out.println("<a href = UserHomePage/?userId=" + author.getId()+ ">"
                + author.getUsername() + "</a>");%>
    </i>

    <i id = "date_created">
        <% out.println(blog.getCreated_at());%>
    </i>
</li>

<form action = "/SaveBlogServlet?blogId=<%out.println(blogId);%>" id = "save_blog" method="post">
    <input type = "submit" value = "Save This Blog" id = "submit_save">
</form>

<div class="separator">Comments</div>

<form id = "add_comment" method="post">
    <div>
        <label>Enter your comment text: </label>
    </div>
    <div>
        <input type="text" name = "commentBody" placeholder="Enter your comment" size = "50" required=""/>
        <input type = "submit" value = "Add Comment" id = "submit_comment">
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
                    <% out.println("<a href = UserHomePage/?userId=" + currUser.getId()+ ">"
                            +"<img id = \"comment_avatar\" src = "+ avatar + ">" + "</a>"); %>
                    <% out.println(comment.getText()); %>
                </p>
            </div>
            <div id = "comment_info">
                <li>
                    <i id = "user_name"></i>
                    <% out.println("By: "+ "<a href = UserHomePage/?userId=" + currUser.getId()+ ">"
                            + currUser.getUsername() + "</a>"); %>
                    <i id = "comment_calendar"></i>
                    <% out.println("On: " + comment.getCreated_at()); %>
                    <i id = "likesNum"></i>
                    <% out.println("Likes: " + comment.getLikes()); %>
                </li>
                <form action = "/LikeDislikeServlet?commentId=<%out.println(comment.getComment_id());%>" id = "like_dislike" method="post">
                    <input type = "submit" name = "like" value="Like" class = "button-style">
                    <input type = "submit" name = "dislike" value="Dislike" class = "button-style">
                </form>
                <% if(comment.getUser_id() == loggedInUserId || loggedInUserId == blog.getCreated_by())
                        out.println("<form action = \"/DeleteCommentServlet?commentId="+ comment.getComment_id() +"\" " +
                                "id = \"delete_blog\" method = \"post\">\n" +
                                "        <input type = \"submit\" value = \"Delete Comment\" class = \"button-style\">\n" +
                                "    </form>");
                %>
            </div>
    </ul>
</div>

<%
    }
%>
</body>
</html>
