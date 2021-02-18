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
        <h1>Lista de Grupo(s) Muscular(es)</h1>

        <table>

            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Descrição</th>
                    <th colspan="2">Editar</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="musclegroup" items="${musclegroups}">
                    <tr>
                        <td>${musclegroup.id}</td>
                        <td>${musclegroup.name}</td>
                        <td>${musclegroup.description}</td>
                        <td><a href="${pageContext.request.contextPath}/DeleteMuscleGroup?idmusclegroup=${musclegroup.id}">Excluir</a></td>
                        <td><a href="${pageContext.request.contextPath}/LoadMuscleGroup?idmusclegroup=${musclegroup.id}">Alterar</a></td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>

        <a href="${pageContext.request.contextPath}/index.html">Home</a>
    </body>
</html>
