<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.mysql.cj.protocol.Resultset" %>
<%@ page import="model.User" %><%--
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
    ResultSet blogs = (ResultSet) session.getAttribute("blogs");
    User user = (User) session.getAttribute("user");
%>
<html>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <head>
        <style>
            div.NavigationBar{
                padding: 10px 16px;
                background: #555;
                color: #f1f1f1;
            }
            img{
                width: 70px;
            }
            .sticky {
                position: fixed;
                top: 0;
                width: 100%;
            }

            .sticky + div.Blogs {
                padding-top: 102px;
            }

            a.Blogs{
                color: crimson;
                text-decoration: none;
                font-size: 30px;
            }

            div.Buttons{
                float: right;
                left:100px;
            }
            a.Buttons:link, a.Buttons:visited {
                background-color: black;
                color: white;
                padding: 14px 25px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
            }

            a.Buttons:hover, a.Buttons:active {
                background-color: #031534;
            }

        </style>
    </head>
    <body>
        <div class="NavigationBar" id="MyNavigationBar">
            <h1 style="font-size: 40px"><%=user.getName()%></h1>
            <img src="<%=user.getAvatar()%>" id="Avatar"/>
            <div class="Buttons">
                <a class="Buttons" href="/UserHomePage"> Add a Category </a>
                <a class="Buttons" href="/AddBlog"> Add a blog</a>
                <%if(user.IsAdmin() == true){%>
                <a class="Buttons" href="/UserHomePage">Admin's HomePage</a>
                <%}%>
            </div>
        </div>
        <div class="Blogs" id="UsersBlogs">
            <p style="font-size: 40px">Blogs</p>
            <%
                while(blogs.next()){
            %>
            <p class="DatesAndBlogs">
                <a class="Blogs" href = /UserHomePage> <%=blogs.getString("title")%> <br> </a>
            <p>
                <%
                    String result = "";
                    String str = blogs.getString("text");
                    int i = 0;
                    int count = 0;
                    while(i < str.length()){
                        result = result + str.charAt(i);
                        if(str.charAt(i) == ' '){
                            count += 1;
                        }
                        if(count == 10){
                            i = str.length();
                            result += "...";
                        }
                        i += 1;
                    }
                %>
                <%=result%>
            </p>
                <%=blogs.getString("created_at")%> <br><br>
            </p>
            <%
                }
            %>
        </div>
        <script>
            window.onscroll = function() {myFunction()};

            var header = document.getElementById("MyNavigationBar");
            var sticky = header.offsetTop;

            function myFunction() {
                if (window.pageYOffset > sticky) {
                    header.classList.add("sticky");
                } else {
                    header.classList.remove("sticky");
                }
            }
        </script>
    </body>
</html>