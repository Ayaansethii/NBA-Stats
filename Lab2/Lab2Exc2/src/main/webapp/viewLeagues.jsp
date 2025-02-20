<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    ArrayList<String> leagues = null;
    if (session != null) {
        leagues = (ArrayList<String>) session.getAttribute("leagues");
    }
    String searchQuery = request.getParameter("searchQuery");
    ArrayList<String> filteredLeagues = new ArrayList<>();

    if (leagues != null && searchQuery != null && !searchQuery.trim().isEmpty()) {
        for (String league : leagues) {
            if (league.toLowerCase().contains(searchQuery.toLowerCase())) {
                filteredLeagues.add(league);
            }
        }
    } else {
        filteredLeagues = leagues;
    }
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
        <h1>Available Leagues</h1>
        <form method="get" action="viewLeagues.jsp">
            <label for="searchQuery">Search Leagues:</label>
            <input type="text" id="searchQuery" name="searchQuery">
            <button type="submit">Search</button>
        </form>
        <h2>Leagues</h2>
        <table>
            <tr>
                <th>League Name</th>
            </tr>
            <% if (filteredLeagues != null && !filteredLeagues.isEmpty()) {
                for (String league : filteredLeagues) { %>
                    <tr>
                        <td><%= league %></td>
                    </tr>
            <%  }
            } else { %>
                <tr>
                    <td>No Leagues Found!</td>
                </tr>
            <% } %>
        </table>
    </div>
</body>
</html>