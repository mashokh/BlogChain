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
            out.println("<h1>" + comment.getText() + "</h1>");
            out.println("<h2>" + comment.getCreated_at() + "</h2>");
        }
    %>
    <form method="post">
        <label>Enter your comment text: </label>
        <input type="text" name = "commentBody"/>
        <input type = "submit" value = "Add Comment">
    </form>
</body>
</html>
