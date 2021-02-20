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
        <h1>Cadastro de Videoaula</h1>
        <form name="savevideolesson" action="${pageContext.request.contextPath}/SaveVideoLesson" method="POST">
            <label for="idvideolesson">ID: </label><input type="number" name="idvideolesson" id="idvideolesson" value="${videolesson.id}" readonly/>
            <label for="namevideolesson">Nome: </label><input type="text" name="namevideolesson" id="namevideolesson" value="${videolesson.name}" required/>
            <label for="descriptionvideolesson">Descrição: </label>
            <textarea type="text" name="descriptionvideolesson" id="descriptionvideolesson" rows="5" cols="60" required>${videolesson.description}</textarea>
            <label for="linkvideolesson">Link: </label><input type="text" name="linkvideolesson" id="linkvideolesson" value="${videolesson.link}" required/>
            <label for="datevideolesson">Data de publicação: </label><input type="date" name="datevideolesson" id="datevideolesson" value="${videolesson.publicationDate}" required/>
          
            <input type="radio" name="status" value="true" checked/> <label for="status">Ativo </label><br />
            <input type="radio" name="status" value="false" checked/> <label for="status">Inativo </label><br />
            
            <input type="submit" name="save" value="Salvar" />
            <h3>${return}</h3>
        </form>
        <a href="${pageContext.request.contextPath}/index.html">Home</a>
    </body>
</html>
