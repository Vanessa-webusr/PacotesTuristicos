<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Pacotes de Viagem</title>
<script src="${pageContext.request.contextPath}/script/filter.js"></script>
</head>
<body>

	<%@include file="cabecalho.jsp"%>
	<div align="center">
		<h1>Lista de Pacote de Viagens</h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a> &nbsp;&nbsp;&nbsp;
			<c:if test="${usuario.agencia != null}"><a href="/<%= contextPath%>/pacote/cadastro">Adicione Novo Pacote</a></c:if>
		</h2>
	</div>

	<div align="center"><form>
		<input type="text" class="filtro" id="id" onkeyup="filtro(0,'id')" placeholder="Pesquisar por id">
		<input type="text" class="filtro" id="cidade" onkeyup="filtro(1,'cidade')" placeholder="Pesquisar por cidade">
		<input type="text" class="filtro" id="estado"onkeyup="filtro(2, 'estado')" placeholder="Pesquisar por estado">
		<input type="text" class="filtro" id="pais"onkeyup="filtro(3, 'pais')" placeholder="Pesquisar por país">
		<input type="date" class="filtro" id="dataInicio" onchange="filtroData(4)" placeholder="Pesquisar por data">
		<input type="date" class="filtro" id="dataFim" onchange="filtroData(4)" placeholder="Pesquisar por data">
		<input type="number" class="filtro" id="duracao" onchange="filtroNumero(5, 'duracao')" placeholder="Pesquisar por duracao" min="1">
		<input type="number" class="filtro" id="valor" onchange="filtroNumero(6, 'valor')" placeholder="Pesquisar por valor" min="0.01" step="any" size="5">
		<c:if test="${!filtrado}">
		<label for="agencia">Agencia</label>
		<select class="filtroSelect" id="agencia" name="agencia" onchange="filtro(8, 'agencia')">
			<option value="" selected>Todos</option>
			<c:forEach items="${listaAgencia}" var="agencia">
				<option value="${agencia.cnpj}">
					${agencia.nome}</option>
			</c:forEach>
		</select>
		</c:if>
		<input type="button" value="Pesquisar por data vigente" onclick="filtroVigente(4)">
		<input type="reset" value="Limpar Filtro" onclick="limpar()">
		</form></div>

	<div align="center">
		<table border="1" id="tabela">
			<caption>Lista de Pacotes</caption>
			<tr>	
				<th>ID</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>Pais</th>
				<th>Data de partida</th>
				<th>Duracao</th>
				<th>Valor</th>
				<th>Descricao</th>
				<th>CNPJ</th>
				<th>Imagens</th>
				<c:if test="${filtrado}">
					<th>Ações</th>
				</c:if>	
			</tr>
			<c:forEach var="pacote" items="${requestScope.listaPacote}">
				<tr>
					
					<td>${pacote.id}</td>
					<td><a href="/<%= contextPath%>/compra/apresentacao?id=${pacote.id}">${pacote.cidade}</a></td>
					<td>${pacote.estado}</td>
					<td>${pacote.pais}</td>
					<td>${pacote.partida}</td>
					<td>${pacote.duracao}</td>
					<td>${pacote.valor}</td>
					<td>${pacote.descricao}</td>
					<td>${pacote.cnpj}</td>
					<td>
					<c:forEach var="imagem" items="${pacote.imagem}">
						<c:forEach var="link" items="${imagem.link}">
							<img src="${link}" width=50 height=50>
						</c:forEach>
					</c:forEach>
					</td>
					<c:if test="${filtrado}">
					<td><a href="/<%= contextPath%>/pacote/edicao?id=${pacote.id}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/<%= contextPath%>/pacote/remove?id=${pacote.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a></td></c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>