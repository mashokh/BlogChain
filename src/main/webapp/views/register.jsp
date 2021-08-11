<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="../css/register.css">
    <title>Register</title>
</head>
<body>

<div class="navbar-container">
    <a href="/">Home</a>
</div>

<div id="register-container">

    <form method = "post">
        <div id="register-title">Welcome to BlogChain</div>
        <div id="register-error">
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
        <div id="password-repeat-container">
            <div class="controls">
                <input type="password" name="password-repeat" class="password-repeat-input textinput textInput" placeholder="Confirm Password" maxlength="16" required="" id="id_password_repeat">
            </div>
        </div>
        <div id="avatar-container" >
            <label>
                <input type="radio" name="avatar" value="black" checked>
                <img id="default-avatar" src="../icons/black.svg">
            </label>
            <label>
                <input type="radio" name="avatar" value="purple">
                <img src="../icons/purple.svg">
            </label>
            <label>
                <input type="radio" name="avatar" value="blue">
                <img src="../icons/blue.svg">
            </label>
            <label>
                <input type="radio" name="avatar" value="green">
                <img src="../icons/green.svg">
            </label>
            <label>
                <input type="radio" name="avatar" value="red">
                <img src="../icons/red.svg">
            </label>
        </div>
        <div id="register-submit-container">
            <button class="register-submit-btn" type="submit">Register</button>
        </div>
        <div id="to-login-btn-container">
            <a class="to-login-btn" href="/login" >Login</a>
        </div>

    </form>
</div>

</body>
</html>