<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>

	<div class="container">
		<h1><spring:message code="alumno.nuevo.titulo" /></h1>

	<mvc:form method="post" action="add-alumno" modelAttribute="alumnoEdit">
	<mvc:errors path="*" cssClass="text-warning"/>
		<div class="form-row">
			<div class="col">
				<mvc:input path="dni" type="text" required="required"
				class="form-control" minlength="9" />
				<mvc:label path="dni"><spring:message code="etiqueta.dni" />:</mvc:label>
				<mvc:errors path="dni" cssClass="text-warning" />
			</div>
			<div class="col">
				<mvc:label path="nombre"><spring:message code="etiqueta.nombre" />:</mvc:label>
				<mvc:input path="nombre" type="text" required="required"
					class="form-control" minlength="5" />
				<mvc:errors path="nombre" cssClass="text-warning" />
			</div>
			<div class="col">
				<mvc:label path="edad"><spring:message code="etiqueta.edad" />:</mvc:label>
				<mvc:input class="form-control" type="number" path="edad"
					required="required" minlength="2" />
				<mvc:errors path="edad" cssClass="text-warning" />
			</div>
			<div class="col">
				<mvc:label path="ciclo"><spring:message code="etiqueta.ciclo" />:</mvc:label>
				<mvc:input class="form-control" type="text" path="ciclo"
					minlength="2" required="required" />
				<mvc:errors path="ciclo" cssClass="text-warning" />
			</div>
			<div class="col">
				<mvc:label path="curso"><spring:message code="etiqueta.curso" />:</mvc:label>
				<mvc:input class="form-control" type="number" path="curso"
					minlength="1" required="required" />
				<mvc:errors path="curso" cssClass="text-warning" />

			</div>
			</div>
			<br/>
			<div class="form-row">
				<div class="col">
					<mvc:checkbox path="erasmus"/>
					<mvc:label path="erasmus"><spring:message code="etiqueta.erasmus" /></mvc:label>
				</div>
				<div class="col">
					<mvc:label path="interesadoEn"><spring:message code="etiqueta.interesado" /></mvc:label>
					<mvc:checkboxes path="interesadoEn" items="${interesadoEnLista}"/>
				</div>
				<div class="col">
					<mvc:label path="lenguajeFavorito"><spring:message code="etiqueta.lenguaje.favorito" /></mvc:label><br/>
					<mvc:checkbox path="lenguajeFavorito" value="Java"/>&nbsp;Java
				</div>
			</div>
			<div class="form-row">
				<div class="col">
					<mvc:label path="genero"><spring:message code="etiqueta.genero" /></mvc:label><br/>
					<ul style="list-style-type: none;">
					<c:forEach items="${generoLista}" var="genero">
					<li><mvc:radiobutton path="genero" value="${genero}"/>${genero}</li>
					</c:forEach>
				</div>
				<div class="col">
					<mvc:label path="horario"><spring:message code="etiqueta.horas" /></mvc:label><br/>
					<mvc:select path="horario">
					<mvc:options path="horario" items="${horarioLista}"/>
					</mvc:select>
				</div>
				<div class="col">
					<mvc:label path="pais"><spring:message code="etiqueta.pais" /></mvc:label><br/>
					<mvc:select path="pais">
					<mvc:option value="-" label="Ninguno"/>
					<mvc:options items="${paisLista}"/>
					</mvc:select>
				</div>
				<div class="col">
				<mvc:label path="matriculadoEn"><spring:message code="etiqueta.matriculado" /></mvc:label><br/>
				<mvc:select path="matriculadoEn" items="${moduloLista}" itemLabel="nombre">
					</mvc:select>
				</div>
				<div class="form-row">
					<div class="col">
						<mvc:label path="hobbies"><spring:message code="etiqueta.hobbies" /></mvc:label><br/>
						<mvc:textarea path="hobbies" rows="3" cols="70" />
					</div>
				</div>
		</div>
		<br><input type="submit" value="Añadir" class="btn btn-success">
	</mvc:form>

</div>

<%@ include file="../jspf/footer.jspf" %>

	<script src="webjars/jquery/3.5.1/jquery.min.js"></script>
	<script src="webjars/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="webjars/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>