<%@ page import="java.util.ArrayList" %>
<%@ page import="model.User" %>
<%@ page import="model.Blogs" %>
<%@ page import="model.Category" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.08.2021
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>

<%
    ArrayList<Blogs> blogs = (ArrayList<Blogs>) session.getAttribute("blogs");
    User user = (User) session.getAttribute("user");
    int loggedInUserId = (Integer) (session.getAttribute("loggedInUserId"));
    int homePageUserId = (Integer) (session.getAttribute("homePageUserId"));
    ArrayList<Category> categories = (ArrayList<Category>) session.getAttribute("categories");
%>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><%=user.getUsername() + "'s page"%></title>
        <link rel="stylesheet" type="text/css" media="screen" href="../css/UserHomePage.css">
    </head>
    <body>
        <div class="NavigationBar" id="MyNavigationBar">
            <h1 style="font-size: 40px"><%=user.getUsername()%></h1>
            <img src="<%="../icons/" + user.getAvatar() + ".svg"%>" id="Avatar"/>
            <div class="Buttons">
                <% if(loggedInUserId == homePageUserId){%>
                    <button class="AddButtons" id="CategoryButton">Add a Category</button>
                    <button class="AddButtons" id="BlogButton">Add a Blog</button>
                    <%if(user.getAdmin()){%>
                        <a class="Buttons" href="/">Admin's HomePage</a>
                    <%}
                }%>

            </div>
        </div>
        <div class="Blogs" id="UsersBlogs">
            <p style="font-size: 40px">Blogs</p>
            <%
                for(int i = 0; i < blogs.size(); i++){
            %>
            <p class="DatesAndBlogs">
                <a class="Blogs" href = /> <%=blogs.get(i).getTitle()%> </a>
                <%if(loggedInUserId == homePageUserId){%>
                    <form action="/DeleteBlog?blogTitle=<%=blogs.get(i).getTitle()%>" method="post">
                        <input type="submit" name="delete" value="delete" />
                    </form>
                <%}%>
            </p>
                <%=blogs.get(i).getCreated_at()%>
                <%
                    String result = "";
                    String str = blogs.get(i).getText();
                    int j = 0;
                    int wordsToShow = 10;
                    while(j < str.length()){
                        result = result + str.charAt(j);
                        if(str.charAt(j) == ' '){
                            wordsToShow -= 1;
                        }
                        if(wordsToShow == 0){
                            j = str.length();
                            result += "...";
                        }
                        j += 1;
                    }
                %>
                <%=result%>
            <%
                }
            %>
        </div>
        <div id="blogModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">&times;</span>
                    <h2>Add a Blog</h2>
                </div>
                <div class="modal-body">
                    <form method = "post" id="blogContainer">
                        <div id="titleContainer">
                            <div class="controls">
                                <input type="text" name="blogTitle" title="title" class="BlogTitle" placeholder="Title" maxlength="16" required="" id="Title">
                                <button class="AddBlogButton" type="submit">Add</button>
                            </div>
                        </div>
                        <label for="categories">choose a category:</label>
                        <select name="chosen_category" id="categories">
                            <%for(int i = 0; i < categories.size(); i++){%>
                                <option value=<%=categories.get(i).getName()%>><%=categories.get(i).getName()%>></option>
                            <%}%>
                        </select>
                        <br><br>
                    </form>
                    <textarea required="" name="blogText" cols="40" rows="5" form="blogContainer"></textarea>
                </div>
            </div>

        </div>
        <div id="categoryModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">&times;</span>
                    <h2>Request to Add a Category</h2>
                </div>
                <div class="modal-body">
                    <form method = "post" id="categoryContainer">
                        <div id="categoryNameContainer">
                            <div class="controls">
                                <input type="text" name="category" title="category" class="CategoryName" placeholder="Enter category" maxlength="16" required="" id="Category">
                                <button class="AddCategoryButton" type="submit">Add</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>
         <script src="../JS/UserHomePage.js"></script>
    </body>
</html>