<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
		String contextPath = request.getContextPath().replace("/", "");
%>
<table>
    <tr>
        <td>
            <a href="/<%= contextPath%>/views/login.jsp">Login</a>
            <a href="/<%= contextPath%>/pacote/lista">Lista de Pacotes</a>
            <a href="/<%= contextPath%>/agencia/lista">Lista de Agencias</a>
            <a href="/<%= contextPath%>/cliente/lista">Lista de Clientes</a>
        </td>
    </tr>
</table>