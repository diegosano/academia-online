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
        <h1>Lista de funcionário(s)</h1>

        <table>

            <thead>
                <tr>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Telefone/Celular</th>
                    <th>E-mail</th>
                    <th>Currículo</th>
                    <th colspan="2">Editar</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <td>${employee.name}</td>
                        <td>${employee.cpf}</td>
                        <td>${employee.telephone}</td>
                        <td>${employee.email}</td>
                        <td><a href="${pageContext.request.contextPath}/employee/${employee.curriculumVitae}">Baixar</a></td>
                        <td><a href="${pageContext.request.contextPath}/DeleteEmployee?idperson=${employee.id}&curriculum=${employee.curriculumVitae}">Excluir</a></td>
                        <td><a href="${pageContext.request.contextPath}/LoadEmployee?idperson=${employee.id}">Alterar</a></td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>

        <a href="${pageContext.request.contextPath}/index.html">Home</a>
    </body>
</html>
