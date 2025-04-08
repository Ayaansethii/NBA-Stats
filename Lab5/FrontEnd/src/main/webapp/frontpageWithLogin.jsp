<%@ page import="javax.servlet.http.HttpSession" %>
<html>
<head>
    <title>Welcome to Basketball App</title>
    <link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
    <h2>Welcome, ${sessionScope.username}!</h2>
    <p>You are logged in as a manager.</p>
    <a href="createLeague.jsp">Create League</a> | 
    <a href="searchLeague.jsp">Search Leagues</a>
</body>
</html>
