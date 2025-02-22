<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Persistence.League_CRUD" %>
<%
    HttpSession sessionUser = request.getSession(false);
    String username = (sessionUser != null) ? (String) sessionUser.getAttribute("username") : null;

    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");

    ArrayList<String> leagues = League_CRUD.getAllLeagues(); 
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

        <% if (successMessage != null) { %>
            <p style="color: green;"><%= successMessage %></p>
        <% } %>
        <% if (errorMessage != null) { %>
            <p style="color: red;"><%= errorMessage %></p>
        <% } %>

        <h2>Create a League</h2>
        <form action="CreateLeagueServlet" method="post">
            <label for="leagueName">League Name:</label>
            <input type="text" id="leagueName" name="leagueName" required>
            <button type="submit">Create League</button>
        </form>

        <h2>Existing Leagues</h2>
        <ul>
            <% for (String league : leagues) { %>
                <li><%= league %></li>
            <% } %>
        </ul>
    </div>
</body>
</html>
