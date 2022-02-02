<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>
<h1 class="display-4" align="center" style="color: red">${error}</h1>

	<div class="container">
		<h1><spring:message code="alumnos.titulo" /></h1>
		<p><spring:message code="home.bienvenida" /> ${nombre}</p>
		
		<mvc:form action="/jcpicot_primer_app_spring_mvc/filter-alumno" method="post"
		modelAttribute="filtroAlumno">
		<div class="row">
			<div class="col">
				<mvc:label path="type"><spring:message code="boton.filtrar" /></mvc:label>
				<div class="input-group">
					<div class="input-group">
						<mvc:select class="form-control" path="type">
							<mvc:options items="${searchLista}" path="type" />
						</mvc:select>
					</div>
				</div>
			</div>
			<div class="col">
				<mvc:label path="value"><spring:message code="etiqueta.como" /></mvc:label>
				<div class="form-group">
					<mvc:input path="value" class="form-control w-75 d-inline-block"
						type="text" id="value" placeholder="Search"></mvc:input>
					<input type="submit" class="btn btn-success" value="Filter"></input>
				</div>
			</div>
		</div>
	</mvc:form>
		
		<table class="table table-striped">
		<thead class="thead-light">
			<tr>
				<th><a href="list-alumno.do?orden=dni"><spring:message code="etiqueta.dni" /></a></th>
				<th><a href="list-alumno.do?orden=nombre"><spring:message code="etiqueta.nombre" /></a></th>
				<th><a href="list-alumno.do?orden=edad"><spring:message code="etiqueta.edad" /></a></th>
				<th><a href="list-alumno.do?orden=ciclo"><spring:message code="etiqueta.ciclo" /></a></th>
				<th><a href="list-alumno.do?orden=curso"><spring:message code="etiqueta.curso" /></a></th>
				<th><a href="list-alumno.do?orden=erasmus"><spring:message code="etiqueta.erasmus" /></a></th>
				
				<th><spring:message code="etiqueta.accion" /></th>
			</tr>
		</thead>
			
			<c:forEach items="${alumnos}" var="alumno">
				<tr>
				<td>${alumno.getDni()}</td>
				<td>${alumno.getNombre()}</td>
				<td>${alumno.getEdad()}</td>
				<td>${alumno.getCiclo()}</td>
				<td>${alumno.getCurso()}</td>
				<td><input type="checkbox" ${alumno.check()} disabled="disabled"/></td>
				<td> <a class="btn btn-success" href="/jcpicot_primer_app_spring_mvc/update-alumno?dni=${alumno.getDni()}"><spring:message code="boton.modificar" /></a> </td>
				<td> <a class="btn btn-danger" href="/jcpicot_primer_app_spring_mvc/del-alumno?dni=${alumno.getDni()}"><spring:message code="boton.borrar" /></a> </td>
				<td> <a class="btn btn-success" href="/jcpicot_primer_app_spring_mvc/list-documentacion?dni=${alumno.getDni()}
						&nombre=${alumno.getNombre()}&ciclo=${alumno.getCiclo()}&curso=${alumno.getCurso()}"><spring:message code="boton.documentacion" /></a> </td>
				
				</tr>
			</c:forEach>
			
		</table>
		
		<a class="btn btn-success" href="add-alumno"><spring:message code="alumno.nuevo.titulo" /></a>


	</div>

<%@ include file="../jspf/footer.jspf" %>

	<script src="webjars/jquery/3.5.1/jquery.min.js"></script>
	<script src="webjars/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="webjars/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>