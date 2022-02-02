<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>

<div class="container">
<h1><spring:message code="modulos.titulo" /></h1>
<h3><spring:message code="home.bienvenida" /> ${usuario.getNombre()}</h3>

<table class="table table-striped">
<thead class="thead-dark">
<th><a style="color: white;" href="list-modulo?criterio=">ID</a></th>
<th><a style="color: white;" href="list-alumno?criterio=nombre"><spring:message code="etiqueta.nombre" /></a></th>
<th><a style="color: white;" href="list-modulo?criterio=horas"><spring:message code="etiqueta.horas" /></a></th>
<th><a style="color: white;" href="list-modulo?criterio=abv"><spring:message code="etiqueta.abreviatura" /></a></th>
<th><spring:message code="etiqueta.accion" /></th>
</thead>
<tbody>

</tbody>
<c:forEach items="${modulos}" var="modulo">
<tr>
	<td>${modulo.getId()}</td>
	<td>${modulo.getNombre()}</td>
	<td>${modulo.getHoras()}</td>
	<td>${modulo.getAbreviatura()}</td>
</tr>
</c:forEach>
</table>
</div>"
<%@ include file="../jspf/footer.jspf" %>
