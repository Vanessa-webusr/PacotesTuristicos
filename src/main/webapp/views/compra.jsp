<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
	<title>Compra pacote</title>
	<link rel="stylesheet" href="../style/styleSheet.css">
	</head>
	<body>
	
		<%@include file="cabecalho.jsp"%>
		<div align="center">
			<h1>Compra de Pacote</h1>
		</div>
		 <div align="center" id="compra">
		 	<p><b>Pacote Escolhido:</b></p>
			<p><b>Pacote: </b>${pacote.id}</p>
			<p><b>Destino: </b>${pacote.cidade}, ${pacote.estado}, ${pacote.pais}</p>
			<p><b>Data de partida:</b>${pacote.partida}</p>
			<p><b>Duração da viagem:</b> ${pacote.duracao}</p>
			<p><b>Valor do pacote:</b> ${pacote.valor}</p>
			<p><b>Descrição:</b> ${pacote.descricao}</p>
			<p><b>CNPJ agência:</b> ${pacote.cnpj}</p>
			<h4>Fotos:</h4>
			<c:forEach var="imagem" items="${pacote.imagem}">
				<c:forEach var="link" items="${imagem.link}">
					<img src="${link}" width=200 height=200>
				</c:forEach>
			</c:forEach>
			<br><br>
			<form action="/<%=contextPath%>/compra/insere" method="get">
				<input type="hidden" name="idPacote" value="${pacote.id}">
				<input type="hidden" name="idCliente" value="${usuarioLogado.cliente.id}">
				<input type="number" id="valor" name="valor" required
			min="0.01" step="any" size="5" value="${pacote.valor}">
				<input type="submit" value="Comprar">
			</form>
		</div>
	</body>
</html>