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
        <h1>Cadastro de cliente</h1>

        <form name="saveuser" action="${pageContext.request.contextPath}/SaveUser" method="post" enctype="multipart/form-data">
            <label for="iduser">ID: </label><input type="number" name="iduser" id="iduser" value="${user.id}" readonly/>
            <label for="nameuser">Nome: </label><input type="text" name="nameuser" id="nameuser" value="${user.name}" required/>
            <label for="nameuser">CPF:  </label><input type="text" name="cpfuser" id="cpfuser" value="${user.cpf}" required/>
            <label for="nameuser">Data de nascimento:  </label><input type="date" name="birthdaydateuser" id="birthdaydateuser" value="${user.birthdayDate}" required/>
            <label for="nameuser">Foto:  </label><input type="file" name="profilepictureuser" id="profilepictureuser" /> <img src="${pageContext.request.contextPath}/ShowUserProfilePicture?idperson=${user.id}" width="100" height="100" />
            <label for="nameuser">E-mail:  </label><input type="email" name="emailuser" id="emailuser" value="${user.email}" required/>
            <label for="nameuser">Senha:  </label><input type="password" name="passworduser" id="passworduser" value="${user.password}" required/>
            <input type="submit" name="save" value="Salvar" />
            <h3>${return}</h3>
        </form>
        <a href="${pageContext.request.contextPath}/index.html">Home</a>
    </body>
</html>
