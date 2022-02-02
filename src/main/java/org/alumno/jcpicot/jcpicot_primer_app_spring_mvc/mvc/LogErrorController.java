package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.mvc;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.LogError;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Pagina;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Usuario;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.I18nService;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.LogErrorService;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"loginName","loginNickName","usuario"})

public class LogErrorController{
	
	Pagina paginaLogError = new Pagina("Errores", "list-logerror");
	
	@Autowired
	PaginaService servicioPagina;
	@Autowired
	LogErrorService servicioLogError;
	@Autowired
	I18nService language;
	
	@RequestMapping(value = "list-logerror", method = RequestMethod.GET)
	public String listarLogError(@RequestParam(required = false) String ordenar ,@RequestParam(required = false) String campoFiltro,
			@RequestParam(required = false) String textoFiltro,ModelMap model) {
		Usuario user = (Usuario) model.getAttribute("usuario");
		paginaLogError.setIdioma(language.getIdioma());
		servicioPagina.setPagina(paginaLogError);
		
		if (user.getNickname() == "") {
			servicioLogError.addLogError(new LogError(servicioLogError.asignarId(),"Acceso denegado","Usuario no registrado ha intentado entrar"));
			return "redirect:login";
		}
		
		 model.put("pagina", paginaLogError);
		 if (ordenar != null) {
			 model.put("listaErrores", servicioLogError.listaErrores(ordenar));
		 }
		 if ((campoFiltro != null) && (textoFiltro != null)) {
			 model.put("listaErrores", servicioLogError.listaConFiltro(campoFiltro, textoFiltro));
		 }else {
			 model.put("listaErrores", servicioLogError.listaErrores());
		 }
		 return "list-logerror";
	}
}