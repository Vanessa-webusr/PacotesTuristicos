<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Agencias</title>
<script src="${pageContext.request.contextPath}/script/filter.js"></script>
<link rel="stylesheet" href="../style/styleSheet.css">
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

	<div align="center"><form id="filtro">
		<input type="text" class="filtro" id="id" onkeyup="filtro(0,'id')" placeholder="Pesquisar por id">
		<input type="text" class="filtro" id="email" onkeyup="filtro(1,'email')" placeholder="Pesquisar por email">
		<input type="text" class="filtro" id="cnpj"onkeyup="filtro(2, 'cnpj')" placeholder="Pesquisar por cnpj">
		<input type="text" class="filtro" id="nome"onkeyup="filtro(3, 'nome')" placeholder="Pesquisar por nome">
		<input type="reset" value="Limpar Filtro" onclick="limpar()">
		</form></div>

	<div align="center">
		<table id="tabela">
			<caption>Lista de Agencia</caption>
			<tr>
				<th>ID</th>
				<th>E-mail</th>
				<th>CNPJ</th>
				<th>Nome</th>
				<th>Descricao</th>
				<th>Ações</th>
			</tr>
			<c:forEach var="agencia" items="${requestScope.listaAgencia}">
				<tr>
					<td>${agencia.id}</td>
					<td>${agencia.email}</td>
					<td>${agencia.cnpj}</td>
					<td>${agencia.nome}</td>
					<td>${agencia.descricao}</td>
					<td><a href="/<%= contextPath%>/agencia/edicao?id=${agencia.id}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/<%= contextPath%>/agencia/remove?id=${agencia.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>