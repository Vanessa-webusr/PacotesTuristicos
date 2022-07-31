<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
	<caption>
		<c:choose>
			<c:when test="${agencia != null}">
                            Edição
                        </c:when>
			<c:otherwise>
                            Cadastro
                        </c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${agencia != null}">
		<input type="hidden" name="id" value="${agencia.id}" />
	</c:if>
	<tr>
		<td><label for="email">E-mail</label></td>
		<td><input type="text" id="email" name="email" size="45"
			required value="${agencia.email}" /></td>
	</tr>
	<tr>
		<td><label for="senha">Senha</label></td>
		<td><input type="password" id="senha" name="senha" size="45" required
			value="${agencia.senha}" /></td>
	</tr>
	<tr>
		<td><label for="cnpj">CNPJ</label></td>
		<td><input type="number" id="cnpj" name="cnpj" size="20" required
			min="0" value="${agencia.cnpj}" /></td>
	</tr>
	<tr>
		<td><label for="nome">Nome</label></td>
		<td><input type="text" id="nome" name="nome" size="45" required
			value="${agencia.nome}" /></td>
	</tr>
	<tr>
		<td><label for="descricao">Descricao</label></td>
		<td><input type="text" id="descricao" name="descricao" size="256" required
			value="${agencia.descricao}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" id="submit"/></td>
	</tr>
</table>