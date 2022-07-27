<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Agencias</title>
</head>
<body>

	<%@include file="cabecalho.jsp"%>
	<div align="center">
		<h1>Gerenciamento de Pacote de Agencia</h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a> &nbsp;&nbsp;&nbsp; <a
				href="/<%= contextPath%>/agencia/cadastro">Adicione Nova Agencia</a>
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Agencia</caption>
			<tr>
				<th>Ações</th>
				<th>ID</th>
				<th>E-mail</th>
				<th>Senha</th>
				<th>CNPJ</th>
				<th>Nome</th>
				<th>Descricao</th>
			</tr>
			<c:forEach var="agencia" items="${requestScope.listaAgencia}">
				<tr>
					<td><a href="/<%= contextPath%>/agencia/edicao?id=${agencia.id}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/<%= contextPath%>/agencia/remove?id=${agencia.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a></td>
					<td>${agencia.id}</td>
					<td>${agencia.email}</td>
					<td>${agencia.senha}</td>
					<td>${agencia.cnpj}</td>
					<td>${agencia.nome}</td>
					<td>${agencia.descricao}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>