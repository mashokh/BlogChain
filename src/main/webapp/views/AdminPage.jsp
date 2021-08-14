<!-- Author: Giorgi Arabidze. -->

<%@ page import="model.Category" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="../css/AdminPage.css">
    <title>Admin Page</title>
</head>
<body>

<h2>Admin Control Panel</h2>

<form action="/" method="get">
    <button type="submit" id="getToUserHomePage">User Homepage</button><br><br>
</form>

<button id="openSuggestedCategoriesModal">Review Suggestions</button><br><br>
<button id="openApprovedCategoriesModal">Approved Categories</button><br><br>
<button id="openAddCategoryModal">Add Category</button><br><br>
<button id="openDeleteCategoryModal">Delete Category</button><br><br>
<button id="openBlogDeletionModal">Delete Blog</button><br><br>

<% ArrayList<Category> suggestedCategories = (ArrayList) request.getAttribute("suggestedCategories"); %>
<% ArrayList<Category> approvedCategories = (ArrayList) request.getAttribute("approvedCategories"); %>

<div class="modal fade" id="suggestedCategoriesModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Suggested Categories</h4>
            </div>
            <div class="modal-body">
                <table cellspacing="20" cellpadding="2">
                    <tr><th>Category name</th><th>Approve Category</th><th>Deny Category</th></tr>
                    <%
                        Iterator<Category> iterator = suggestedCategories.iterator();
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
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="approvedCategoriesModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Approved Categories</h4>
            </div>
            <div class="modal-body">
                <table cellspacing="20" cellpadding="2">
                    <tr><th>Category name</th></tr>
                    <%
                        iterator = approvedCategories.iterator();
                        while(iterator.hasNext()){
                            String name = iterator.next().getName();
                    %>
                    <tr><td><%=name%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="addCategoryModal" action="">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Add Category Manually</h4>
            </div>
            <div class="modal-body">
                <form action="/AddCategory" method="post">
                    <label for="addCategoryName">Category Name:</label>
                    <input type="text" id="addCategoryName" name="addCategoryName" required> <br><br>
                    <button type="submit">Add</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="deleteCategoryModal" action="">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Delete Category Manually</h4>
            </div>
            <div class="modal-body">
                <form action="/DeleteCategory" method="post">
                    <label for="deleteCategoryName">Category Name:</label>
                    <input type="text" id="deleteCategoryName" name="deleteCategoryName" required> <br><br>
                    <button type="submit">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="deleteBlogModal" action="">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Delete Blog</h4>
            </div>
            <div class="modal-body">
                <form action="/AdminBlogDeletion" method="post">
                    <label for="blogTitle">Blog Title:</label>
                    <input type="text" id="blogTitle" name="blogTitle" required> <br><br>
                    <label for="username">Author Username:</label>
                    <input type="text" id="username" name="username" required> <br><br>
                    <button type="submit">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>






<script>
    // Modal for Suggested Categories
    var suggestedCategoriesModal = document.getElementById("suggestedCategoriesModal");
    var suggestedCategoriesButton = document.getElementById("openSuggestedCategoriesModal");
    var span0 = document.getElementsByClassName("close")[0];

    suggestedCategoriesButton.onclick = function() {
        suggestedCategoriesModal.style.display = "block";
    }

    span0.onclick = function() {
        suggestedCategoriesModal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == suggestedCategoriesModal) {
            suggestedCategoriesModal.style.display = "none";
        }
    }


    //Modal to Show Approved Categories
    var approvedCategoriesModal = document.getElementById("approvedCategoriesModal");
    var approvedCategoriesButton = document.getElementById("openApprovedCategoriesModal");
    var span1 = document.getElementsByClassName("close")[1];

    approvedCategoriesButton.onclick = function() {
        approvedCategoriesModal.style.display = "block";
    }

    span1.onclick = function() {
        approvedCategoriesModal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == approvedCategoriesModal) {
            approvedCategoriesModal.style.display = "none";
        }
    }

    // Modal for Category Addition
    var addCategoryModal = document.getElementById("addCategoryModal");
    var addCategoryButton = document.getElementById("openAddCategoryModal");
    var span2 = document.getElementsByClassName("close")[2];

    addCategoryButton.onclick = function() {
        addCategoryModal.style.display = "block";
    }

    span2.onclick = function() {
        addCategoryModal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == addCategoryModal) {
            addCategoryModal.style.display = "none";
        }
    }


    // Modal for Category Deletion
    var deleteCategoryModal = document.getElementById("deleteCategoryModal");
    var deleteCategoryButton = document.getElementById("openDeleteCategoryModal");
    var span3 = document.getElementsByClassName("close")[3];

    deleteCategoryButton.onclick = function() {
        deleteCategoryModal.style.display = "block";
    }

    span3.onclick = function() {
        deleteCategoryModal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == deleteCategoryModal) {
            deleteCategoryModal.style.display = "none";
        }
    }

    // Modal for Blog Deletion
    var deleteBlogModal = document.getElementById("deleteBlogModal");
    var deleteBlogButton = document.getElementById("openBlogDeletionModal");
    var span4 = document.getElementsByClassName("close")[4];

    deleteBlogButton.onclick = function() {
        deleteBlogModal.style.display = "block";
    }

    span4.onclick = function() {
        deleteBlogModal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == deleteBlogModal) {
            deleteBlogModal.style.display = "none";
        }
    }



</script>
</body>
</html>
