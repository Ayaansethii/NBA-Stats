<%@ page import="javax.servlet.http.HttpSession" %>
<%
    String username = (String) session.getAttribute("username");

    if (username == null) {
        response.sendRedirect("login.jsp");
        return; 
    }

    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");
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

        <h2>Create a League</h2>
        <form action="CreateLeagueServlet" method="post">
            <label for="leagueName">League Name:</label>
            <input type="text" id="leagueName" name="leagueName" required>
            <button type="submit">Create League</button>
        </form>

        <% if (successMessage != null) { %>
            <p><%= successMessage %></p>
        <% } %>
        <% if (errorMessage != null) { %>
            <p><%= errorMessage %></p>
        <% } %>

        <h2>My Leagues</h2>
        <table>
            <tr>
                <th>League Name</th>
            </tr>
            <%
                java.util.List<String> leagues = (java.util.List<String>) session.getAttribute("leagues");
                if (leagues != null) {
                    for (String league : leagues) {
            %>
                <tr>
                    <td><%= league %></td>
                </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</body>
</html>