<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page import="br.ufscar.dc.dsw.domain.Cliente"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
		String contextPath = request.getContextPath().replace("/", "");
%>
<div id="cabecalho">
<table>
    <tr>
        <td>
            <c:if test="${usuarioLogado != null}">
                Bem-vindo, <c:if test="${usuarioLogado.cliente != null}">${usuarioLogado.cliente.nome}</c:if><c:if test="${usuarioLogado.agencia != null}">${usuarioLogado.agencia.nome}</c:if>!
            </c:if>
            <a href="/<%= contextPath%>/pacote/lista">Lista de Pacotes</a>
            <c:if test="${usuarioLogado.cliente != null}">
                <c:if test="${usuarioLogado.cliente.admin == 1}">
                    <a href="/<%= contextPath%>/agencia/lista">Lista de Agencias</a>
                    <a href="/<%= contextPath%>/cliente/lista">Lista de Clientes</a>
                </c:if>
            </c:if>
            <c:if test="${usuarioLogado.agencia != null}">
                <a href="/<%= contextPath%>/pacote/listaPorAgencia">Meus Pacotes</a>
            </c:if>
            <c:if test="${usuarioLogado.cliente != null}">
                <a href="/<%= contextPath%>/compra/lista">Meus Pacotes</a>
            </c:if>
            <c:if test="${usuarioLogado == null}">
                <a href="/<%= contextPath%>/views/login.jsp">Login</a>
            </c:if>
            <c:if test="${usuarioLogado != null}">
                <a href="/<%= contextPath%>/auth/logout">Logout</a>
            </c:if>
        </td>
    </tr>
</table>
</div>  