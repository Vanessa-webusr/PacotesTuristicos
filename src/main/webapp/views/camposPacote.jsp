<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
	<caption>
		Cadastro
	</caption>
	<tr>
		<td><label for="CNPJ">CNPJ</label></td>
		<td><input type="text" id="CNPJ" name="CNPJ" size="45"
			required value="teste" /></td>
	</tr>
	<tr>
        <th>Destino:</th>
		<td><label for="pais">País</label></td>
		<td><input type="text" id="pais" name="pais" size="45" required
			value="teste" /></td>
	</tr>
	<tr>
		<td><label for="estado">Estado</label></td>
		<td><input id="estado" name="estado" size="2" required value="teste"/>
		</td>
	</tr>
    <tr>
		<td><label for="cidade">Cidade</label></td>
		<td><input id="cidade" name="cidade" size="45" required value="teste"/>
		</td>
	</tr>
	<tr>
		<td><label for="partida">Data de partida</label></td>
		<td><input type="date" id="partida" name="partida" required value="teste" /></td>
	</tr>
    <tr>
		<td><label for="duracao">Duracao (em dias)</label></td>
		<td><input type="number" id="duracao" name="duracao" required value="teste" /></td>
	</tr>
	<tr>
		<td><label for="preco">Preço</label></td>
		<td><input type="number" id="preco" name="preco" required
			min="0.01" step="any" size="5" value="teste" /></td>
	</tr>
    <tr>
        <th>Fotos</th>
		<td><input type="image" id="imagem1" name="imagem1" required
			value="teste" /></td>
        <td><input type="image" id="imagem2" name="imagem2"
			value="teste" /></td>
        <td><input type="image" id="imagem3" name="imagem3"
			value="teste" /></td>
        <td><input type="image" id="imagem4" name="imagem4"
			value="teste" /></td>
        <td><input type="image" id="imagem5" name="imagem5"
            value="teste" /></td>
        <td><input type="image" id="imagem6" name="imagem6"
			value="teste" /></td>
        <td><input type="image" id="imagem7" name="imagem7"
			value="teste" /></td>
        <td><input type="image" id="imagem8" name="imagem8"
			value="teste" /></td>
        <td><input type="image" id="imagem9" name="imagem9"
			value="teste" /></td>
        <td><input type="image" id="imagem10" name="imagem10"
            value="teste" /></td>      
	</tr>
    <tr>
        <td><label for="descricao">Descricao</label></td>
		<td><input type="text" id="descricao" name="descricao" required
            size="256" value="teste" /></td>
    </tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
	</tr>
</table>