<%@ page import="model.Comments" %>
<%@ page import="model.CommentsDao" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lukaa
  Date: 09-Aug-21
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
    String blogId = request.getParameter("blogId");
    ArrayList<Comments> comments = CommentsDao.getCommentsByBlogId(Integer.parseInt(blogId));
%>
<html>
<head>
    <title>
        <%
            out.println("Viewing blog: " + blogId);
        %>
    </title>
</head>
<body>
    <%
        for (Comments comment : comments) {
    %>

    <div class = "comment">
        <ul class = "user_comment">
            <div class = "user_avatar">
                <img src = "">
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
                        <%out.println(comment.getUser_id());%>
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
