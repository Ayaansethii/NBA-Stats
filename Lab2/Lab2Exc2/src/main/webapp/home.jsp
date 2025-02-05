<%
    String username = (String) session.getAttribute("username");

    if (username == null) {
        response.sendRedirect("login.jsp");
        return; 
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link rel="stylesheet" href="login.css">
</head>
<body>
    <div class="main">
        <h1>Welcome, <%= username %>!</h1>
        <p>You are now logged in.</p>
        
    </div>
</body>
</html>
