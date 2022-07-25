<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Pacotes de Viagem</title>
</head>
<body>

	<%@include file="cabecalho.jsp"%>
	<div align="center">
		<h1>Gerenciamento de Pacote de Viagens</h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a> &nbsp;&nbsp;&nbsp; <a
				href="/<%= contextPath%>/pacote/cadastro">Adicione Novo Pacote</a>
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
				<th>Imagens 1</th>
				<th>Imagens 2</th>
				<th>Imagens 3</th>
				<th>Imagens 4</th>
				<th>Imagens 5</th>
				<th>Imagens 6</th>
				<th>Imagens 7</th>
				<th>Imagens 8</th>
				<th>Imagens 9</th>
				<th>Imagens 10</th>
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
					<c:forEach var="imagem" items="${pacote.imagem}">
						<c:forEach var="link" items="${imagem.link}">
							<td><img src="${link}" width=50 height=50></td>
						</c:forEach>
					</c:forEach>
					<td>${pacote.descricao}</td>
					<td>${pacote.cnpj}</td>
					<td><a href="/<%= contextPath%>/pacote/edicao?id=${pacote.id}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/<%= contextPath%>/pacote/remove?id=${pacote.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>