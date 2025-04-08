<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Search Leagues</title>
    <link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
    <h2>Search Leagues</h2>
    <form action="LeagueSearchServlet" method="get">
        Search Query: <input type="text" name="query"/><br/>
        <input type="submit" value="Search"/>
    </form>
    <c:if test="${not empty searchResults}">
        <div>
            <h3>Results:</h3>
            <pre>${searchResults}</pre>
        </div>
    </c:if>
</body>
</html>
