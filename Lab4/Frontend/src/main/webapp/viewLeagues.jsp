<%@ page import="java.util.ArrayList" %>
<%
    ArrayList<String> filteredLeagues = (ArrayList<String>) request.getAttribute("filteredLeagues");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Leagues</title>
    <link rel="stylesheet" href="login.css">
</head>
<body>
    <div class="main">
        <h1>Search Leagues</h1>
        <form method="post" action="SearchLeagueServlet">
            <label for="searchQuery">Search:</label>
            <input type="text" id="searchQuery" name="searchQuery">
            <button type="submit">Search</button>
        </form>

        <h2>Results</h2>
        <table>
            <tr><th>League Name</th></tr>
            <% if (filteredLeagues != null && !filteredLeagues.isEmpty()) { 
                for (String league : filteredLeagues) { %>
                    <tr><td><%= league %></td></tr>
            <%  }} else { %>
                <tr><td>No Leagues Found!</td></tr>
            <% } %>
        </table>
    </div>
</body>
</html>
