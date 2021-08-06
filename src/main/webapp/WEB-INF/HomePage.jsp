<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.08.2021
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            left : 600px;
            top : 120px;
        }
    </style>
</head>

<body>
<h1>Name</h1>
<form action="/HomePage">
    <input type="button" value="add category"/>
</form>
<form action = "/HomePage">
    <input type = "button" value = "add blog"/>
</form>
</body>
</html>