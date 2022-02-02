package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Pagina;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Usuario;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.I18nService;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.LoginService;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//@SessionAttributes("usuario")
@SessionAttributes({"loginName","loginNickName","usuario"})

public class LoginController {
	@Autowired
	LoginService loginService = new LoginService();
	@Autowired
	PaginaService paginas = new PaginaService();
	Pagina pagina = new Pagina("login", "login");
	@Autowired
	I18nService language = new I18nService();

	@RequestMapping(value = {"/login","/"}, method = RequestMethod.GET)
	public String mostrarLogin(HttpServletRequest request,HttpServletResponse response, Locale locale,ModelMap model) {
		//Traza i18n
		//Información idioma de la petición del navegador
		System.out.println("Accept-Language: "+ request.getHeader("Accept-Language"));

		//Información del localeResolver
		
		System.out.println(String.format("Petición recibida. Languaje: %s, País: %s %n", locale.getLanguage(), locale.getDisplayCountry()));
		//Datos para la cabecera de la página
		language.configuraIdiomaPeticion(request, response, locale);
		paginas.setPagina(pagina);
		model.put("pagina", new Pagina("Home","login", language.getIdioma()));
		System.out.println("language.getIdioma "+language.getIdioma());
		model.addAttribute("loginName", "Desconocido");
		model.put("loginNickname", "Desconocido");
		//Datos para el formulario de login
		model.addAttribute("usuario", new Usuario("","","",""));
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String procesaLogin(String errores, ModelMap model, @Valid Usuario usuari, BindingResult validacion) {
		if (validacion.hasErrors()) {
			return "login";
		}
		model.addAttribute("usuario", usuari);
		model.addAttribute("loginName", usuari.getNombre());
		model.addAttribute("loginNickName", usuari.getNickname());
		paginas.setPagina(pagina);
		model.addAttribute("pagina", paginas.getPagina());
		if (loginService.usuarioValido(usuari)) {
			System.out.println("bienvenida");
			return "bienvenida";
		}
		model.put("errores", "Usuario "+usuari.getNickname()+" o contraseña incorrectos");
		return "login";
	}
}
