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

        <h1>Menu</h1>
        <a href="musclegroup/save.jsp">Cadastrar Grupo Muscular</a> <br />
        <a href="ListMuscleGroup">Listar Grupo Muscular</a> <br />

        <a href="DataVideoLesson">Cadastrar Videoaulas</a> <br />
        <a href="ListVideoLesson">Listar Videoaulas</a> <br />

        <a href="user/save.jsp">Cadastrar Cliente</a> <br />
        <a href="ListUser">Listar Cliente(s)</a> <br />

        <a href="employee/save.jsp">Cadastrar Funcionário</a> <br />
        <a href="ListEmployee">Listar Funcionário(s)</a> <br />

        <form action="${pageContext.contextPath}/Login" method="POST">
            <label for="email"></label><input type="text" name="email" id="email" required/>
            <label for="password"></label><input type="password" name="password" id="password" required/>
            <input type="hidden" name="action" id="action" value="login" />
            <input type="submit" value="Entrar" />
        </form>
    </body>
</html>
