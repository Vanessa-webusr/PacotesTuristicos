<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Agencias</title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1>Gerenciamento de Agencia</h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a> &nbsp;&nbsp;&nbsp; <a
				href="/<%=contextPath%>/cliente/cadastro">Adicione Nova Agencia</a>
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Agencia</caption>
			<tr>
				<th>ID</th>
				<th>E-mail</th>
				<th>Senha</th>
				<th>CNPJ</th>
				<th>Nome</th>
				<th>Descricao</th>
			</tr>
			<c:forEach var="cliente" items="${requestScope.listaLivros}">
				<tr>
					<td>${}</td>
					<td>${}</td>
					<td>${}</td>
					<td>${}</td>
					<td>${}</td>
					<td>${}</td>
					<td><a href="/<%= contextPath%>/livros/edicao?id=${livro.id}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/<%= contextPath%>/livros/remocao?id=${livro.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>