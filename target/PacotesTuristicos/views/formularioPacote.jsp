<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
    <title>Cadastro de Pacotes</title>
	<link rel="stylesheet" href="../style/styleSheet.css">
</head>
<body>
	<%@include file="cabecalho.jsp"%>
	<div align="center">
		<h1>Gerenciamento de Pacotes de Viagem</h1>
		<h2>
			<a href="/<%= contextPath%>/pacote/lista">Voltar para a lista de Pacotes</a>
		</h2>
	</div>
	<div align="center">
		<c:choose>
			<c:when test="${pacote != null}">
				<form action="atualiza" method="post" id="formulario">
					<%@include file="camposPacote.jsp"%>
				</form>
			</c:when>
			<c:otherwise>
				<form action="insere" method="post" id="formulario">
					<%@include file="camposPacote.jsp"%>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	<c:if test="${!empty requestScope.mensagens}">
		<ul class="erro">
			<c:forEach items="${requestScope.mensagens}" var="mensagem">
				<li>${mensagem}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>

</html>