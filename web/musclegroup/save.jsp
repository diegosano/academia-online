<%-- 
    Document   : save
    Created on : Feb 16, 2021, 7:18:02 PM
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
        <h1>Cadastro de Grupo Muscular</h1>
        <form name="savemusclegroup" action="${pageContext.request.contextPath}/SaveMuscleGroup" method="POST">
            <label for="idmusclegroup">ID: </label><input type="number" name="idmusclegroup" id="idmusclegroup" readonly/>
            <label for="namemusclegroup">Nome: </label><input type="text" name="namemusclegroup" id="namemusclegroup" required/>
            <label for="descriptionmusclegroup">Descrição: </label>
            <textarea type="text" name="descriptionmusclegroup" id="descriptionmusclegroup" rows="5" cols="60" required></textarea>
            <input type="submit" name="save" value="Salvar" />
            <h3>${return}</h3>
        </form>
    </body>
</html>
