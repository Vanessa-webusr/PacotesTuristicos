<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${livro != null}">
                            Edição
                        </c:when>
			<c:otherwise>
                            Cadastro
                        </c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${livro != null}">
		<input type="hidden" name="id" value="${livro.id}" />
	</c:if>
	<tr>
		<td><label for="email">E-mail</label></td>
		<td><input type="text" id="email" name="email" size="45"
			required value="${}" /></td>
	</tr>
	<tr>
		<td><label for="senha">Senha</label></td>
		<td><input type="password" id="senha" name="senha" size="45" required
			value="${}" /></td>
	</tr>
	<tr>
		<td><label for="cpf">CPF</label></td>
		<td><input type="number" id="cpf" name="cpf" size="11" required
			min="0" value="${}" /></td>
	</tr>
	<tr>
		<td><label for="telefone">Telefone</label></td>
		<td><input type="number" id="telefone" name="telefone" size="12" required
			min="0" value="${}" /></td>
	</tr>
	<tr>
		<td><label for="homem">Homem</label></td>
		<td><input type="radio" id="homem" name="sexo"
            value="homem" /></td>
        <td><label for="mulher">Mulher</label></td>
		<td><input type="radio" id="Mulher" name="sexo"
            value="mulher" /></td>
        <td><label for="outro">Outro</label></td>
		<td><input type="radio" id="outro" name="sexo"
            value="outro" /></td>
	</tr>
    <tr>
        <td><label for="nascimento">Data de Nascimento</label></td>
        <td><input type="date" id="nascimento" name="nascimento" required
        value="${}"></td>
    </tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
	</tr>
</table>