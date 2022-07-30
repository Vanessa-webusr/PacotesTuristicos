<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
	<caption>
		<c:choose>
			<c:when test="${cliente != null}">
                            Edição
                        </c:when>
			<c:otherwise>
                            Cadastro
                        </c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${cliente != null}">
		<input type="hidden" name="id" value="${cliente.id}" />
	</c:if>
	<tr>
		<td><label for="email">E-mail</label></td>
		<td><input type="text" id="email" name="email" size="45"
			required value="${cliente.email}" /></td>
	</tr>
	<tr>
		<td><label for="senha">Senha</label></td>
		<td><input type="password" id="senha" name="senha" size="45" required
			value="${cliente.senha}" /></td>
	</tr>
	<tr>
		<td><label for="cpf">CPF</label></td>
		<td><input type="number" id="cpf" name="cpf" size="11" required
			min="0" value="${cliente.cpf}" /></td>
	</tr>
	<tr>
		<td><label for="nome">Nome</label></td>
		<td><input type="text" id="nome" name="nome" size="45"
			required value="${cliente.nome}" /></td>
	</tr>
	<tr>
		<td><label for="telefone">Telefone</label></td>
		<td><input type="number" id="telefone" name="telefone" size="12" required
			min="0" value="${cliente.telefone}" /></td>
	</tr>
	<tr>
		<td><label>Sexo</label></td>
		<td><label for="homem">Homem</label>
		<input type="radio" id="homem" name="sexo"
            value="H" ${cliente.sexo=='H' ? 'checked' : '' }/>
        <label for="mulher">Mulher</label>
		<input type="radio" id="mulher" name="sexo"
            value="M" ${cliente.sexo=='M' ? 'checked' : '' }/>
        <label for="outro">Outro</label>
		<input type="radio" id="outro" name="sexo"
            value="O"${cliente.sexo=='O' ? 'checked' : '' }/></td>
	</tr>
    <tr>
        <td><label for="nascimento">Data de Nascimento</label></td>
        <td><input type="date" id="nascimento" name="nascimento" required
        value="${cliente.nascimento}"></td>
    </tr>
	<tr>
		<th><label for="admin">Administrador</label></th>
		<td><label for="nao">Não</label>
		<input type="radio" id="nao" name="admin"
            value="0" ${cliente.admin==1 ? 'checked' : '' }/>
        <label for="sim">Sim</label>
		<input type="radio" id="sim" name="admin"
            value="1" ${cliente.admin==0 ? 'checked' : '' }/></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" id="submit"/></td>
	</tr>
</table>