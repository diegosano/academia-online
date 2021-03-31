<%-- 
    Document   : save
    Created on : Feb 16, 2021, 7:18:02 PM
    Author     : Diego
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Academia Online</title>
    </head>
    <body>
        <h1>Lista de cliente(s)</h1>

        <table>

            <thead>
                <tr>
                    <th>Foto</th>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Data de nascimento</th>
                    <th>E-mail</th>
                    <th>Data de cadastro</th>
                    <th colspan="2">Editar</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td><img src="${pageContext.request.contextPath}/ShowUserProfilePicture?idperson=${user.id}" width="100" height="100" /></td>
                        <td>${user.name}</td>
                        <td>${user.cpf}</td>
                        <td><fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${user.birthdayDate}"></fmt:formatDate></td>
                        <td>${user.email}</td>
                        <td><fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${user.registratioDate}"></fmt:formatDate></td>
                        <td><a href="${pageContext.request.contextPath}/DeleteUser?idperson=${user.id}">Excluir</a></td>
                        <td><a href="${pageContext.request.contextPath}/LoadUser?idperson=${user.id}">Alterar</a></td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>

        <a href="${pageContext.request.contextPath}/home.html">Home</a>
    </body>
</html>
