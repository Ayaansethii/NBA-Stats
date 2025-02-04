<%
    // No need to declare HttpSession again, it's already available in JSP
    String username = (String) session.getAttribute("username");

    // Redirect to login page if session is null
    if (username == null) {
        response.sendRedirect("login.jsp");
        return; // Ensure execution stops after redirect
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
        <a href="logout.jsp">Logout</a>
    </div>
</body>
</html>
