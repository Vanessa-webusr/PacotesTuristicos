<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Pacotes de Viagem</title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1>Gerenciamento de Pacote de Viagens</h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a> &nbsp;&nbsp;&nbsp; <a
				href="/<%=contextPath%>/api">Adicione Nova Agencia</a>
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Pacotes</caption>
			<tr>
				<th>ID</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>Pais</th>
				<th>Data de partida</th>
				<th>Duracao</th>
				<th>Valor</th>
				<th>Imagens</th>
				<th>Descricao</th>
				<th>CNPJ</th>
			</tr>
			<c:forEach var="pacote" items="${requestScope.listaPacote}">
				<tr>
					<td>${pacote.id}</td>
					<td>${pacote.cidade}</td>
					<td>${pacote.estado}</td>
					<td>${pacote.pais}</td>
					<td>${pacote.partida}</td>
					<td>${pacote.duracao}</td>
					<td>${pacote.valor}</td>
					<td>${pacote.imagens}</td>
					<td>${pacote.descricao}</td>
					<td>${pacote.cnpj}</td>
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