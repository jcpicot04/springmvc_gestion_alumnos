package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.mvc;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.LogError;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Pagina;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Usuario;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.LogErrorService;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.ModuloService;
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

public class ModuloController {
	@Autowired
	ModuloService moduloService;
	@Autowired
	PaginaService paginaService;
	@Autowired
	LogErrorService servicioLogError;
	
	@RequestMapping(value = "list-modulo", method = RequestMethod.GET)
	public String listarAlumno(@RequestParam(required = false) String criterio, ModelMap model) {
		Usuario user = (Usuario) model.getAttribute("usuario");
		if (user.getNickname() == "") {
			servicioLogError.addLogError(new LogError(servicioLogError.asignarId(),"Acceso Denegado","Un usuario no registrado ha intentado entrar"));
			return "redirect:login";
		}
		model.put("modulos", moduloService.listar(criterio == null ? "" : criterio));
		paginaService.setPagina(new Pagina("Lista de módulos", "list-modulo"));
		model.put("pagina", paginaService.getPagina());
		return "list-modulo";
	}
}