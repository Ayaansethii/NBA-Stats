<%@ page import="javax.servlet.http.HttpSession" %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="login.css">
</head>
<body>
    <div class="container">
        <div class="logo">
            <img src="resources/HoopMetricLogo.png" alt="Logo"/>
        </div>
        <div class="main">
            <h1>Login to Your Account</h1>
            <form action="LoginServlet" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
                <% if (errorMessage != null){ %>
                <p><%= errorMessage %></p>
                <%}%>
                <button type="submit">Login</button>
            </form>
        </div>
    </div>
</body>
</html>
