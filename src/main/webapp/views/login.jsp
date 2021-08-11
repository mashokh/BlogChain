<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="../css/login.css">
    <title>Login</title>
</head>
<body>

<div class="navbar-container">
    <a href="/">Home</a>
</div>

<div id="login-container">

    <form method = "post">
        <div id="login-title">Welcome to BlogChain</div>
        <div id="login-error">
            <% if (request.getAttribute("error") != null) { out.print(request.getAttribute("error"));}%>
        </div>
        <div id="nickname-container">
            <div class="controls">
                <input type="text" name="username" class="nickname-input textinput textInput" placeholder="Username" maxlength="16" required="" id="id_nickname">
            </div>
        </div>
        <div id="password-container">
            <div class="controls">
                <input type="password" name="password" class="password-input textinput textInput" placeholder="Password" maxlength="16" required="" id="id_password">
            </div>
        </div>
        <div id="login-submit-container">
            <button class="login-submit-btn" type="submit">Login</button>
        </div>
        <div id="to-register-btn-container">
            <a class="to-register-btn" href="/register" >Register</a>
        </div>
    </form>

</div>
</body>
</html>