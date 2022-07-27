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
			<a href="/<%=contextPath%>">Menu Principal</a> &nbsp;&nbsp;&nbsp;
			<c:if test="${usuario.agencia != null}"><a href="/<%= contextPath%>/pacote/cadastro">Adicione Novo Pacote</a></c:if>
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Pacotes</caption>
			<tr>
				<c:if test="${filtrado}">
					<th>Ações</th>
				</c:if>
				<th>ID</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>Pais</th>
				<th>Data de partida</th>
				<th>Duracao</th>
				<th>Valor</th>
				<th>Descricao</th>
				<th>CNPJ</th>
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
				
			</tr>
			<c:forEach var="pacote" items="${requestScope.listaPacote}">
				<tr>
					<c:if test="${filtrado}">
					<td><a href="/<%= contextPath%>/pacote/edicao?id=${pacote.id}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/<%= contextPath%>/pacote/remove?id=${pacote.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a></td></c:if>
					<td>${pacote.id}</td>
					<td>${pacote.cidade}</td>
					<td>${pacote.estado}</td>
					<td>${pacote.pais}</td>
					<td>${pacote.partida}</td>
					<td>${pacote.duracao}</td>
					<td>${pacote.valor}</td>
					<td>${pacote.descricao}</td>
					<td>${pacote.cnpj}</td>
					<c:forEach var="imagem" items="${pacote.imagem}">
						<c:forEach var="link" items="${imagem.link}">
							<td><img src="${link}" width=50 height=50></td>
						</c:forEach>
					</c:forEach>
					
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>