
<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>

	<div class="container">
		<h1> <spring:message code="home.bienvenida" /> ${usuario.getNickname()}</h1>
	</div>

<%@ include file="../jspf/footer.jspf" %>
