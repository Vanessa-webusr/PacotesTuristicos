<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
	<title>Compra pacote</title>
	</head>
	<body>
	
		<%@include file="cabecalho.jsp"%>
		<div align="center">
			<h1>Compra de Pacote</h1>
		</div>
		 <div align="center">
		 	<p>Este eh um teste</p>
		 	<p>Id: ${pacote.id}</p>
			<p>${pacote.cidade}</p>
			<p>${pacote.estado}</p>
			<p>${pacote.pais}</p>
			<p>${pacote.partida}</p>
			<p>${pacote.duracao}</p>
			<p>${pacote.valor}</p>
			<p>${pacote.descricao}</p>
			<p>${pacote.cnpj}</p>
			<c:forEach var="imagem" items="${pacote.imagem}">
				<c:forEach var="link" items="${imagem.link}">
					<img src="${link}" width=100 height=100>
				</c:forEach>
			</c:forEach>
			<h3><a href="#"
			onclick="return confirm('Tem certeza de que deseja incluir este item ao carrinho?');">Comprar</a></h3>		
		</div>
	</body>
</html>