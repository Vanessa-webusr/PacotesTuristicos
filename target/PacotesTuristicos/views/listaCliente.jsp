<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Clientes</title>
<script src="${pageContext.request.contextPath}/script/filter.js"></script>
</head>
<body>


	<%@include file="cabecalho.jsp"%>
	<div align="center">
		<h1>Gerenciamento de Cliente</h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a> &nbsp;&nbsp;&nbsp; <a
				href="/<%= contextPath%>/cliente/cadastro">Adicione Novo Cliente</a>
		</h2>
	</div>

	<div align="center"><form>
		<input type="text" class="filtro" id="id" onkeyup="filtro(0,'id')" placeholder="Pesquisar por id">
		<input type="text" class="filtro" id="email" onkeyup="filtro(1,'email')" placeholder="Pesquisar por email">
		<input type="text" class="filtro" id="cpf"onkeyup="filtro(2, 'cpf')" placeholder="Pesquisar por cpf">
		<input type="text" class="filtro" id="nome"onkeyup="filtro(3, 'nome')" placeholder="Pesquisar por nome">
		<input type="text" class="filtro" id="telefone"onkeyup="filtro(4, 'telefone')" placeholder="Pesquisar por telefone">
		<input type="text" class="filtro" id="sexo"onkeyup="filtro(5, 'sexo')" placeholder="Pesquisar por sexo">
		<input type="date" class="filtro" id="dataInicio" onchange="filtroData(6)" placeholder="Pesquisar por data">
		<input type="date" class="filtro" id="dataFim" onchange="filtroData(6)" placeholder="Pesquisar por data">
		<input type="reset" value="Limpar Filtro" onclick="limpar()">
		</form></div>

	<div align="center">
		<table border="1" id="tabela">
			<caption>Lista de Clientes</caption>
			<tr>
				<th>ID</th>
				<th>E-mail</th>
				<th>CPF</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Sexo</th>
                <th>Data de nascimento</th>
				<th>Açôes</th>
			</tr>
			<c:forEach var="cliente" items="${requestScope.listaCliente}">
				<tr>
					<td>${cliente.id}</td>
					<td>${cliente.email}</td>
					<td>${cliente.cpf}</td>
					<td>${cliente.nome}</td>
					<td>${cliente.telefone}</td>
                    <td>${cliente.sexo}</td>
                    <td>${cliente.nascimento}</td>
					<td><a href="/<%= contextPath%>/cliente/edicao?id=${cliente.id}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/<%= contextPath%>/cliente/remove?id=${cliente.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>