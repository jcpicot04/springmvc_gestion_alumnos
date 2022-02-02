package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.mvc;

import java.util.ArrayList;

import javax.validation.Valid;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.ImagenUsuario;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Usuario;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.AlumnoService;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.FileService;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.I18nService;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Controller
@SessionAttributes({"loginName","loginNickName","usuario"})

public class UsuarioController{
	@Autowired
	FileService fileService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	AlumnoService alumnoService;
	@Autowired
	I18nService language = new I18nService();


@RequestMapping(value = "/imagenUsuario/{nickName}", method = RequestMethod.GET)
public ResponseEntity<FileSystemResource> getFile(@PathVariable("nickName") String nickName) {

	try {
		System.out.println("nickname "+ nickName);

		String nombreFicheroConImagen;
		if(nickName.equals("Desconocido") || nickName.equals("") ) {
			nombreFicheroConImagen = "Desconocido.jpg";
			System.out.println("nombreFicheroConImagen "+nombreFicheroConImagen);
		}else {
			Usuario user = loginService.encontrarUsuarioPorNickname(nickName);
			nombreFicheroConImagen = user.getNombreFicheroConImagen();
			System.out.println("nombreFicheroConImagen2 "+nombreFicheroConImagen);

		}
		System.out.println("nombreFicheroConImagen3 "+nombreFicheroConImagen);
		FileSystemResource resource = fileService.getImagenUsuario(nombreFicheroConImagen);
		if (!resource.exists()) {
			throw new Exception("La imagen no existe");
		}
		ResponseEntity<FileSystemResource> responseEntity = new ResponseEntity<>(resource, HttpStatus.OK);
		return responseEntity;
	}catch (Exception e) {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
}

@RequestMapping(value= "/update-imagenUsuario", method = RequestMethod.GET)
public String updateImagenUsuario(ModelMap model, @RequestParam String nickName) {
	model.put("loginNickName", nickName);
	model.put("errores","");
	model.addAttribute("imagenUsuario",new ImagenUsuario(nickName));
	return "update-imagenUsuario";
	
	
}
@RequestMapping(value= "/guardar-imagen-usuario", method = RequestMethod.POST)
public String guardarImagenUsuario(ModelMap model, @Valid ImagenUsuario imagenUsuario, BindingResult validacion) {
	Usuario user = (Usuario) model.getAttribute("usuario");
	String nickName=imagenUsuario.getNickname();
	MultipartFile fichero =imagenUsuario.getImagen();
	if (validacion.hasErrors()) {
		return "update-imagenUsuario";
	}
	if(user.getNickname() == "") {
		return "update-imagenUsuario";
	}else {
		user.setNombreFicheroConImagen(imagenUsuario.getImagen().getOriginalFilename());
		fileService.guardaImagenUsuario(imagenUsuario.getImagen(),imagenUsuario.getNickname());
		loginService.modificaUsuario(user,user.getNickname());
		model.addAttribute("imagenUsuario",new ImagenUsuario(imagenUsuario.getNickname()));
	}
	ArrayList<String> listaErroresAlGuardar=fileService.guardaImagenUsuario(fichero, nickName);
	if(!listaErroresAlGuardar.isEmpty()) {
		String mensajeCompleto="";
		for(String mensaje:listaErroresAlGuardar) {
			mensajeCompleto+=language.getTraduccion(mensaje)+"<br>";
		}
	}
	return "update-imagenUsuario";
	
}
}