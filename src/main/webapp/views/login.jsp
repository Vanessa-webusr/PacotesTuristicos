<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autenticação de Usuário</title>
    </head>
    <body>
        <%
		String contextPath = request.getContextPath().replace("/", "");
        %>
        <h1>Autenticação de Usuário</h1>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="/<%= contextPath%>/auth/login">
            <table>
                <tr>
                    <td><label for="cliente">Cliente</label></td>
                    <td><input type="radio" id="cliente" name="tipo"
                        value="Cliente" checked/></td>
                    <td><label for="agencia">Agência</label></td>
                    <td><input type="radio" id="agencia" name="tipo"
                        value="Agencia" /></td></tr>
                <tr>
                    <th>Login: </th>
                    <td><input type="text" name="email"/></td>
                </tr>
                <tr>
                    <th>Senha: </th>
                    <td><input type="password" name="senha" /></td>
                </tr>
                <tr>
                    <td colspan="2"> 
                        <input type="submit" name="OK" value="Entrar"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>