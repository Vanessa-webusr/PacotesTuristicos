<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
	<caption>
		<c:choose>
			<c:when test="${pacote != null}">
                            Edição
                        </c:when>
			<c:otherwise>
                            Cadastro
                        </c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${pacote != null}">
		<input type="hidden" name="id" value="${pacote.id}" />
	</c:if>
	<input type="hidden" name="cnpj" value="${usuario.agencia.cnpj}" />	
	<input type="hidden" name="agencia_id" value="${usuario.agencia.id}" />
	<tr>
		<th>Destino:</th>
	</tr>
	<tr>
		<td><label for="pais">País</label></td>
		<td><input type="text" id="pais" name="pais" size="45" required
			value="${pacote.pais}" /></td>
	</tr>
	<tr>
		<td><label for="estado">Estado</label></td>
		<td><input id="estado" name="estado" size="2" required 
			value="${pacote.estado}"/>
		</td>
	</tr>
    <tr>
		<td><label for="cidade">Cidade</label></td>
		<td><input id="cidade" name="cidade" size="45" required 
			value="${pacote.cidade}"/>
		</td>
	</tr>
	<tr>
		<td><label for="partida">Data de partida</label></td>
		<td><input type="date" id="partida" name="partida" required
			 value="${pacote.partida}" /></td>
	</tr>
    <tr>
		<td><label for="duracao">Duracao (em dias)</label></td>
		<td><input type="number" id="duracao" name="duracao" required 
			min="1" value="${pacote.duracao}" /></td>
	</tr>
	<tr>
		<td><label for="valor">Valor</label></td>
		<td><input type="number" id="valor" name="valor" required
			min="0.01" step="any" size="5" value="${pacote.valor}" /></td>
	</tr>
	<tr>
		<th>Fotos:</th>
	</tr>
	<c:if test="${pacote != null}">
	<c:forEach var="imagem" items="${imagens}" varStatus="i" >
		<tr>
			<td><label for="imagem"> Imagem ${i.count}</label></td>
			<td><input type = "text" id="imagem" name="imagem[]" value="${imagem}"></td>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${pacote == null}">
		<c:forEach var="i" varStatus="i" begin="1" end="10">
			<tr>
					<td><label for="imagem"> Imagem ${i.count}</label></td>
					<td><input type = "text" id="imagem" name="imagem[]"></td>
			</tr>
		</c:forEach>
	</c:if>
    <tr>
        <td><label for="descricao">Descricao</label></td>
		<td><input type="text" id="descricao" name="descricao" required
            size="256" value="${pacote.descricao}" /></td>
    </tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" id="submit"/></td>
	</tr>
</table>