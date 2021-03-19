<%-- 
    Document   : index
    Created on : Mar 13, 2021, 2:31:58 PM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Academia Online</title>
    </head>
    <body>
        <h1>Login page</h1>
        <form action="${pageContext.request.contextPath}/Login" method="POST">
            <label for="email"></label><input type="text" name="email" id="email" required/>
            <label for="password"></label><input type="password" name="password" id="password" required/>
            <input type="hidden" name="action" id="action" value="login" />
            <input type="submit" value="Entrar" />
        </form>
    </body>
</html>
