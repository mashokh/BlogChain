<!-- Author: Giorgi Arabidze. -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message</title>
    <link rel="stylesheet" type="text/css" media="screen" href="../css/AdminPage.css">
</head>
<body>

<% String message = (String) request.getAttribute("message"); %>

<div class="modal fade" id="messageModal" action="">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <form action="/AdminHomePage" method="get">
                    <button type="submit" class="close" data-dismiss="modal" aria-hidden="true" id="closeButton">&times;</button>
                    <h4 class="modal-title">Message</h4>
                </form>

            </div>
            <div class="modal-body">
                <p><%=message%></p>
            </div>
        </div>
    </div>
</div>
</body>

<script>
    var messageModal = document.getElementById("messageModal");
    var span = document.getElementById("closeButton");
    messageModal.style.display = "block";
    span.onclick = function() {
        messageModal.style.display = "none";
    }
</script>
</html>
