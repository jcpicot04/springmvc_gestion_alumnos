<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>

<div class="container">
<h1><spring:message code="log.errores.titulo" /></h1>

<form action="list-logerror" method="GET">

<div class="form-row">
<div class="form-group col-md-4">
<select name="campoFiltro" required>
	<option>ID</option>
	<option><spring:message code="etiqueta.tipo" /></option>
	<option><spring:message code="etiqueta.explicacion" /></option>

</select>
</div>
<div class="form-group col-md-6">
	<label for="textoFiltro"><spring:message code="etiqueta.como" /></label>
	<input type="text" name="textoFiltro" id="textoFiltro">
</div>

<div class="form-group col-md-1">
	<input type="submit" class="btn btn-success" value="Filtrar">
</div>
</div>
</form>

<table class="table table-striped">
	<thead class="thead-dark">
		<th><a style="color: white;" href="list-logerror?ordenar=id">ID</a></th>
		<th><a style="color: white;" href="list-logerror?ordenar=tipo"><spring:message code="etiqueta.tipo" /></a></th>
		<th><a style="color: white;" href="list-logerror?ordenar=info"><spring:message code="etiqueta.explicacion" /></a></th>
		<th><spring:message code="etiqueta.accion" /></th>
	</thead>
	<tbody>
	
	</tbody>
	<c:forEach items="${listaErrores}" var="logerror">
	<tr>
		<td>${logerror.getId()}</td>
		<td>${logerror.getTipo()}</td>
		<td>${logerror.getInfo()}</td>		
	</tr>
	</c:forEach>
	
	</table>
</div>
</div>