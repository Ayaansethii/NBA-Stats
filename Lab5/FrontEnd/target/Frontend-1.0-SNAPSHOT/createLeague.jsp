<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Create League</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>Create League</h2>
    <form action="LeagueCreationServlet" method="post">
        League Name: <input type="text" name="leagueName"/><br/>
        Description: <textarea name="leagueDescription"></textarea><br/>
        <input type="submit" value="Create League"/>
    </form>
    <c:if test="${not empty message}">
        <div class="success">${message}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
</body>
</html>
