<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manager Login</title>
    <link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
    <h2>Manager Login</h2>
    <form action="FrontEnd" method="post">
        Username: <input type="text" name="username"/><br/>
        Password: <input type="password" name="password"/><br/>
        <input type="submit" value="Login"/>
    </form>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
</body>
</html>

