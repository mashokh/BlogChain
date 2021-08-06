<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.08.2021
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%
    String name = (String) session.getAttribute("name");
    ArrayList<String> blogs = (ArrayList<String>) session.getAttribute("blogs");

%>
<html>
<head>
    <style>
        body {background-color: powderblue;}
        h1{
            position: absolute;
            left : 100px;
            top: 100px;
            color: crimson;
        }
        form {
            -webkit-appearance: button;
            -moz-appearance: button;
            appearance: auto;

            text-decoration: slateblue;
            color: #1718ff;
            position: relative;
            left : 900px;
            top : 100px;
        }
        a{
            position: absolute;
            left : 100px;
            top: 150px;
            color: crimson;
        }
    </style>
</head>

<body>
<h1><%=name%>></h1>
<form action="/HomePage">
    <input type="button" value="add category"/>
</form>
<form action = "/HomePage">
    <input type = "button" value = "add blog"/>
</form>
<%
    for(int i = 0; i < blogs.size(); i++){
%>
        <a href = /HomePage > <%=blogs.get(i)%> </a>
<%
	}
%>
</body>
</html>