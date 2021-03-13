<%-- 
    Document   : save
    Created on : Feb 27, 2021, 1:42:37 PM
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
        <h1>Cadastro de funcionário</h1>

        <form name="save" action="${pageContext.request.contextPath}/SaveEmployee" method="post" enctype="multipart/form-data">
            <label for="id">ID: </label><input type="number" name="id" id="id" value="${employee.id}" readonly/>
            <label for="name">Nome: </label><input type="text" name="name" id="name" value="${employee.name}" required/>
            <label for="cpf">CPF:  </label><input type="text" name="cpf" id="cpf" value="${employee.cpf}" required/>
            <label for="birthday-date">Data de nascimento:  </label><input type="date" name="birthday-date" id="birthday-date" value="${employee.birthdayDate}" required/>
            <label for="postal-code">CEP: </label><input type="text" name="postal-code" id="address" value="${employee.postalCode}" required/>
            <label for="address">Endereço: </label><input type="text" name="address" id="address" value="${employee.address}" required/>
            <label for="city">Cidade: </label><input type="text" name="city" id="city" value="${employee.city}" required/>
            <label for="telephone">Telefone/Celular: </label><input type="tel" name="telephone" id="name" value="${employee.telephone}" required/>
            <label for="state">UF: </label>
            <select id="state" name="state"> 
                <option value="SP">SP</option>
            </select>
            <label for="curriculum">Currículo:  </label><input type="file" name="curriculum" id="curriculum" />
            <label for="email">E-mail:  </label><input type="email" name="email" id="email" value="${employee.email}" required/>
            <label for="password">Senha:  </label><input type="password" name="password" id="password" value="${employee.password}" required/>
            <input type="submit" name="save" value="Salvar" />
            <h3>${return}</h3>
        </form>
        <a href="${pageContext.request.contextPath}/index.html">Home</a>
    </body>
</html>
