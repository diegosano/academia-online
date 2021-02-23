<%-- 
    Document   : save
    Created on : Feb 16, 2021, 7:18:02 PM
    Author     : Diego
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Academia Online</title>
    </head>
    <body>
        <h1>Lista de Videoaula(s)</h1>

        <table>

            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Descrição</th>
                    <th>Data de publicação</th>
                    <th>Status</th>
                    <th colspan="2">Editar</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="videolesson" items="${videolessons}">
                    <tr>
                        <td>${videolesson.id}</td>
                        <td>${videolesson.name}</td>
                        <td>${videolesson.description}</td>
                        <td>${videolesson.publicationDate}</td>
                        <td>${videolesson.status}</td>
                        <td><a href="${pageContext.request.contextPath}/DeleteVideoLesson?idvideolesson=${videolesson.id}">Excluir</a></td>
                        <td><a href="${pageContext.request.contextPath}/LoadVideoLesson?idvideolesson=${videolesson.id}">Alterar</a></td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>

        <a href="${pageContext.request.contextPath}/index.html">Home</a>
    </body>
</html>
