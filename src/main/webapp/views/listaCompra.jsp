<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Pacotes de Viagem</title>
<script src="${pageContext.request.contextPath}/script/filter.js"></script>
<link rel="stylesheet" href="../style/styleSheet.css">
</head>
<body onload="changeAtivo()">

	<%@include file="cabecalho.jsp"%>
	<div align="center">
		<h1>Compras de ${usuario.cliente.nome}</h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a> &nbsp;&nbsp;&nbsp;
		</h2>
	</div>

	<div>
		<c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
	</div>

	<div align="center"><form id="filtro">
		<input type="text" class="filtro" id="id" onkeyup="filtro(0,'id')" placeholder="Pesquisar por id">
		<input type="text" class="filtro" id="cidade" onkeyup="filtro(1,'cidade')" placeholder="Pesquisar por cidade">
		<input type="text" class="filtro" id="estado"onkeyup="filtro(2, 'estado')" placeholder="Pesquisar por estado">
		<input type="text" class="filtro" id="pais"onkeyup="filtro(3, 'pais')" placeholder="Pesquisar por país">
		<input type="date" class="filtro" id="dataInicio" onchange="filtroData(4)" placeholder="Pesquisar por data">
		<input type="date" class="filtro" id="dataFim" onchange="filtroData(4)" placeholder="Pesquisar por data">
		<input type="number" class="filtro" id="duracao" onchange="filtroNumero(5, 'duracao')" placeholder="Pesquisar por duracao" min="1">
		<input type="number" class="filtro" id="valor" onchange="filtroNumero(6, 'valor')" placeholder="Pesquisar por valor" min="0.01" step="any" size="5">
		<label for="agencia">Agencia</label>
		<select class="filtroSelect" id="agencia" name="agencia" onchange="filtro(8, 'agencia')">
			<option value="" selected>Todos</option>
			<c:forEach items="${listaAgencia}" var="agencia">
				<option value="${agencia.cnpj}">
					${agencia.nome}</option>
			</c:forEach>
		</select>
		<input type="button" value="Pesquisar por data vigente" onclick="filtroVigente(4)">
		<input type="reset" value="Limpar Filtro" onclick="limpar()">
		</form></div>

	<div align="center">
		<table id="tabela">
			<caption>Lista de Pacotes</caption>
			<tr>	
				<th>ID</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>Pais</th>
				<th>Data de partida</th>
				<th>Duracao</th>
				<th>Valor</th>
				<th>Valor da proposta</th>
				<th>Descricao</th>
				<th>CNPJ</th>
				<th>Imagens</th>
				<th>Ativo</th>
				<th>Ações</th>
			</tr>
			<c:forEach var="compra" items="${requestScope.listaCompra}">
				<tr <c:if test="${compra.ativo != 1}">class="cancelado"</c:if>>
					<td>${compra.pacote.id}</td>
					<td>${compra.pacote.cidade}</td>
					<td>${compra.pacote.estado}</td>
					<td>${compra.pacote.pais}</td>
					<td>${compra.pacote.partida}</td>
					<td>${compra.pacote.duracao}</td>
					<td>${compra.pacote.valor}</td>
					<td>${compra.valor}</td>
					<td>${compra.pacote.descricao}</td>
					<td>${compra.pacote.cnpj}</td>
					<td>
					<c:forEach var="imagem" items="${compra.pacote.imagem}">
						<c:forEach var="link" items="${imagem.link}">
							<img src="${link}" width=50 height=50>
						</c:forEach>
					</c:forEach>
					</td>
					<c:if test="${compra.ativo == 1}"><td>Ativo</td></c:if><c:if test="${compra.ativo != 1}"><td colspan="2">Cancelado</td></c:if>
					<c:if test="${compra.ativo == 1}">
					<td><a class="acoes" href="/<%= contextPath%>/compra/cancelar?id=${compra.id}" onclick="return confirm('Tem certeza de que deseja cancelar esta compra?');">Cancelar compra</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>