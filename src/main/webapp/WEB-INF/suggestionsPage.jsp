<%--
  Created by IntelliJ IDEA.
  User: 99559
  Date: 8/6/2021
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<%@page import="java.util.ArrayList"%>      <%--Importing all the dependent classes--%>
<%@page import="java.util.Iterator"%>
<%@ page import="model.CategoryDao" %>

<% ArrayList<CategoryDao> categories = (ArrayList) request.getAttribute("categories"); %>

<table cellspacing="2" cellpadding="2">
    <tr><th>Category name</th><th>Approve Category</th><th>Deny Category</th></tr>
    <%
            Iterator<CategoryDao> iterator = categories.iterator();
        while(iterator.hasNext()){
                String name = iterator.next().getName();
    %>
    <tr><td><%=name%></td>
        <td><form action="/ApproveCategory" method="post">
            <button type="submit" name = "categoryName" value=<%=name%>>Approve</button>
            </form>
        </td>
        <td><form action="/DenyCategory" method="post">
            <button type="submit" name = "categoryName" value=<%=name%>>Deny</button>
        </form>
        </td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
