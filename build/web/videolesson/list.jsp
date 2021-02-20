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

            <select name="idmusclegroup" id="idmusclegroup">
                <c:forEach var="musclegroup" items="${musclegroups}">
                    <option value="${musclegroup.id}">${musclegroup.name}</option>
                </c:forEach>
            </select>

        <a href="${pageContext.request.contextPath}/index.html">Home</a>
    </body>
</html>
