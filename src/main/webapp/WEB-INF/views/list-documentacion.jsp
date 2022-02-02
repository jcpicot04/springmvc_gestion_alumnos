<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>

<div class="container">
	<table class="table table-striped">
		<thead>
			<tr>
				<th><a href="list-alumno?criterio=dni"><spring:message code="etiqueta.dni" /></a></th>
				<th><a href="list-alumno?criterio=nombre"><spring:message code="etiqueta.nombre" /></a></th>
				<th><a href="list-alumno?criterio=ciclo"><spring:message code="etiqueta.ciclo" /></a></th>
				<th><a href="list-alumno?criterio=curso"><spring:message code="etiqueta.curso" /></a></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${alumnoInfo.getDni()}</td>
				<td>${alumnoInfo.getNombre()}</td>
				<td>${alumnoInfo.getCiclo()}</td>
				<td>${alumnoInfo.getCurso()}</td>
				
				
			</tr>


		</tbody>
	</table>
	<p><spring:message code="alumno.documentacion.explicacion" /></p>


<p>${errores}</p>
	<mvc:form action="/jcpicot_primer_app_spring_mvc/list-documentacion" enctype="multipart/form-data" method="post"
		modelAttribute="docAlumnoEdit">
		<mvc:errors path="*" cssClass="text-warning"></mvc:errors>

		<div class="row">
			<div class="col">
				<mvc:label path="id">Id:</mvc:label>
				<mvc:input class="form-control" path="id" id="id"
					required="required" readonly="true"></mvc:input>

				<mvc:textarea class="form-control" path="comentario" id="comentario"
					required="required"></mvc:textarea>
			</div>
			<div class="col">
			<mvc:label path="fichero"><spring:message code="etiqueta.seleccionar.archivo" /></mvc:label>
			<mvc:input path="fichero" type="file" />	
			</div>

			<div class="col">
				<mvc:label path="tipo"><spring:message code="etiqueta.tipo" /></mvc:label>
				<div class="input-group">
					<div class="input-group">
						<ul style="list-style-type: none;">
							<mvc:radiobuttons items="${tipoLista}" path="tipo" element="li" />
						</ul>
					</div>
				</div>
			</div>
			<mvc:hidden path="dni" />

			<div class="col">
				<input type="submit" class="btn btn-success mt-3" value="Añadir">
			</div>
		</div>
	</mvc:form>

	<table class="table table-striped mt-5">
		<thead>
			<tr>
				<th>Id</th>
				<th><spring:message code="etiqueta.tipo" /></th>
				<th><spring:message code="etiqueta.comentario" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${docsAlumnoList}" var="docs">
				<tr>
					<td>${docs.getId()}</td>
					<td>${docs.getTipo()}</td>
					<td>${docs.getComentario()} (${docs.getTipoFichero()})</td>
					<td>
						<a class="btn btn-info" href="descargar-docAlumno/${alumnoInfo.getDni()}/${docs.getId()}">
						<i class="fas fa-address-card"></i><spring:message code="boton.descargar" /></a>
					</td>				
				</tr>
			</c:forEach>

		</tbody>
	</table>
	
</div>