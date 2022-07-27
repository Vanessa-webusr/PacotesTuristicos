<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Clientes</title>
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

	<div align="center">
		<table border="1">
			<caption>Lista de Clientes</caption>
			<tr>
				<th>Açôes</th>
				<th>ID</th>
				<th>E-mail</th>
				<th>Senha</th>
				<th>CPF</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Sexo</th>
                <th>Data de nascimento</th>
			</tr>
			<c:forEach var="cliente" items="${requestScope.listaCliente}">
				<tr>
					<td><a href="/<%= contextPath%>/cliente/edicao?id=${cliente.id}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/<%= contextPath%>/cliente/remove?id=${cliente.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a></td>
					<td>${cliente.id}</td>
					<td>${cliente.email}</td>
					<td>${cliente.senha}</td>
					<td>${cliente.cpf}</td>
					<td>${cliente.nome}</td>
					<td>${cliente.telefone}</td>
                    <td>${cliente.sexo}</td>
                    <td>${cliente.nascimento}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>