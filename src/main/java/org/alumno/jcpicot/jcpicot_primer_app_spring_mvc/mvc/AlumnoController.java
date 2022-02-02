package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Alumno;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Documentacion;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.FiltroAlumno;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.LogError;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Modulo;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Pagina;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Usuario;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.AlumnoEdit;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.DocAlumnoEdit;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.AlumnoService;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.FileService;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.I18nService;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.LogErrorService;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.ModuloService;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.PaginaService;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.excepciones.AlumnoDuplicadoException;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.util.Ts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.server.ResponseStatusException;

@Controller
@SessionAttributes({"loginName","loginNickName","usuario"})
public class AlumnoController{

	@Autowired
	AlumnoService alumnoService = new AlumnoService();
	ModuloService moduloService = new ModuloService();
	@Autowired
	PaginaService paginas = new PaginaService();
	@Autowired
	LogErrorService logerror = new LogErrorService();
	Pagina pagina = new Pagina("Alumnos","list-alumno");
	FileService fileService = new FileService();
	@Autowired
	I18nService language = new I18nService();
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,true));
	}
	
	@RequestMapping(value="list-alumno",method = RequestMethod.GET)
	public String listarAlumnos(@RequestParam(required = false)String orden,ModelMap model) {
		//model.put("alumnos", alumnoService.listaAlumnos("nombre"));
		pagina.setIdioma(language.getIdioma());
		paginas.setPagina(pagina);
		System.out.println("sesion "+model.getAttribute("loginNickName"));
		Usuario user = (Usuario) model.getAttribute("usuario");
		System.out.println(user.getNickname());
		if (user.getNickname() == "") {
			logerror.addLogError(new LogError(logerror.asignarId(),"Acceso denegado","Usuario no registrado ha intentado entrar"));
			return "redirect:login";
		}
		model.addAttribute("filtroAlumno", new FiltroAlumno("", ""));
		if (orden != null) {
			model.put("alumnos", alumnoService.listaAlumnos(orden));
 		} else {
 			model.put("alumnos", alumnoService.listaAlumnos());
 		}
		model.put("pagina", paginas.getPagina());
		return "list-alumno";
	}
	
	@RequestMapping(value = "/filter-alumno", method = RequestMethod.POST)
	public String filterAlumnoPost(@Valid FiltroAlumno falumno, BindingResult validacion, ModelMap model) {
		paginas.setPagina(new Pagina("Lista alumnos", "list-alumno"));
		model.put("pagina", paginas.getPagina());
		if (validacion.hasErrors())
			return "list-alumno";
	
		model.put("alumnos", alumnoService.filter(falumno));
		return "list-alumno";
	}
	@RequestMapping(value="/add-alumno",method = RequestMethod.GET)
	public String mostrarAlumno(ModelMap model) {
		paginas.setPagina(pagina);
		model.addAttribute("pagina", paginas.getPagina());
		model.addAttribute("alumnoEdit", new AlumnoEdit());
		//ArrayList<Integer> m = new ArrayList<Integer>();
		//m.add(0);
		//model.addAttribute("alumno", new Alumno("Nuevo Alumno", "", 18, "DAW", 2, false, "Hombre", "Mañana", "-", m, ""));
		return "add-alumno";
		
	}
	@ModelAttribute("interesadoEnLista")
	public List<String> getinteresadoEnLista() {
		return alumnoService.listaInteresadoEn();
	}
	@ModelAttribute("generoLista")
	public List<String> getgeneroLista() {
		List<String> I18nLista = language.getTraduccion(alumnoService.listaGenero());
		return I18nLista;
	}
	@ModelAttribute("horarioLista")
	public List<String> getHorarioLista() {
		List<String> I18nLista = language.getTraduccion(alumnoService.listaHorario());
		return I18nLista;
	}
	@ModelAttribute("paisLista")
	public Map<String, String> getPaisLista() {
		Map<String,String> I18nLista = language.getTraduccion(alumnoService.listaPais());
		return I18nLista;
	}
	
	
	@ModelAttribute("searchLista")
	public Map<String, String> getSearchLista() {
		Map<String,String> I18nLista = language.getTraduccion(alumnoService.listaSearch());
		return I18nLista;
	}
	
	@ModelAttribute("tipoLista")
	public List<String> gettipoLista() {
		List<String> I18nLista = language.getTraduccion(alumnoService.listaTipo());
		return I18nLista;
	}
	
	@ModelAttribute("moduloLista")
	public Map<Integer,Modulo> getModuloLista() {
		return moduloService.listaMatriculado();
	}
	
	@RequestMapping(value="/add-alumno",method = RequestMethod.POST)
	public String addAlumno(ModelMap model, @Valid AlumnoEdit alumn, BindingResult validacion) {
		if (validacion.hasErrors()) {
			model.addAttribute("alumno", alumn);
			return "add-alumno";
		}
		
		String errores="";
		paginas.setPagina(pagina);
		model.addAttribute("pagina",paginas.getPagina());
		try {
			alumnoService.addAlumno(alumn);
			model.clear();
			return "redirect:list-alumno";
		}catch (AlumnoDuplicadoException e) {
			errores=e.toString();
			model.addAttribute("errores",errores);
			return "add-alumno";
		}
		
	}
	
	@RequestMapping(value="/update-alumno",method = RequestMethod.GET)
	public String updateAlumnos(ModelMap model, @RequestParam String dni) {
		paginas.setPagina(pagina);
		model.addAttribute("pagina", paginas.getPagina());
		model.addAttribute("alumno", alumnoService.encontrarAlumnoPorDni(dni));
		return "update-alumno";
	}
	
	@RequestMapping(value="/update-alumno",method = RequestMethod.POST)
	public String updateAlumnos(ModelMap model, @Valid AlumnoEdit alumno, BindingResult validacion) {
		if (validacion.hasErrors()) {
			return "update-alumno";
		}
		String errores="";
		paginas.setPagina(pagina);
		try {
			alumnoService.modificaAlumno(alumno, model.getAttribute("usuario").toString());
			model.clear();
			return "redirect:list-alumno";
		}catch (Exception e) {
			System.out.print("catch2");
			errores=e.toString();
			model.addAttribute("errores", e.getMessage());
			return "update-alumno";
		}
		
	}
	
	@RequestMapping(value = "del-alumno", method = RequestMethod.GET)
	public String delAlumno(@RequestParam String dni, ModelMap model) {
		alumnoService.delAlumno(alumnoService.encontrarAlumnoPorDni(dni));

		model.clear();

		return "redirect:list-alumno?ordenar=";
	}
	
	@RequestMapping(value = "/list-documentacion", method = RequestMethod.GET)
	public String docAlumno(@RequestParam String dni, @RequestParam(required = false) String nombre,
			@RequestParam(required = false) String ciclo, @RequestParam(required = false) String curso,
			ModelMap model) {
		//paginas.setPagina(new Pagina("Documentación alumnos", "list-alumno"));
		//model.put("pagina", paginas.getPagina());
		//Alumno selected = alumnoService.encontrarAlumnoPorDni(dni);
		//model.put("alumno", selected);
		//model.put("docs", selected.getDocs());
		//model.addAttribute("documentacion", new Documentacion(selected.getDni(), selected.getDocs().size(), "", ""));
	pagina.setIdioma(language.getIdioma());
	model.addAttribute("alumnoInfo",alumnoService.encontrarAlumnoPorDni(dni));
	System.out.println(alumnoService.encontrarAlumnoPorDni(dni));
	model.addAttribute("docAlumnoEdit",new DocAlumnoEdit(alumnoService.siguienteDoc(dni),dni));
	model.addAttribute("docsAlumnoList", alumnoService.encontrarDocsAlumnoListPorDni(dni));
	model.addAttribute("pagina",pagina);
		return "list-documentacion";
	}

	@RequestMapping(value = "/list-documentacion", method = RequestMethod.POST)
	public String docAlumnoPost(@Valid DocAlumnoEdit docAlumnoEdit, BindingResult validacion, ModelMap model) {
		//paginas.setPagina(new Pagina("Lista alumnos", "list-alumno"));
		//model.put("pagina", paginas.getPagina());
		//Alumno alumnoSel = alumnoService.encontrarAlumnoPorDni(documentacion.getDni());
		//if (validacion.hasErrors()) 
			//return "list-documentacion?dni=" + alumnoSel.getDni();
		//if (null == model.getAttribute("usuario")) 
			//return "redirect:list-documentacion?dni=" + alumnoSel.getDni();
		//alumnoSel.setUser(((Usuario) model.getAttribute("usuario")).getNombre());
		//alumnoSel.setTs(Ts.today());
		//model.put("alumno", alumnoSel);
		//alumnoService.addAlumnoDoc(documentacion);
		//model.addAttribute("documentacion", new Documentacion("", alumnoSel.getDocs().size(), "", ""));
		//model.put("docs", alumnoSel.getDocs());
		//String ext = fileService.getExtensionMultipartFile(documentacion.getFichero());
		//String nombreFichero = documentacion.getDni()+"_idDoc_"+documentacion.getId();
		//fileService.guardaDocumentoAlumno(documentacion.getFichero(), nombreFichero);
		//documentacion.setTipoFichero(ext);
		//documentacion.setContentTypeFichero(documentacion.getFichero().getContentType());
		//return "redirect:list-documentacion?dni=" + alumnoSel.getDni();
		String dni = "";
		String errores = "";
		System.out.println("dni docalumnoedit "+docAlumnoEdit.getDni());
		if(docAlumnoEdit != null)
			dni = docAlumnoEdit.getDni();
		pagina.setIdioma(language.getIdioma());
		model.addAttribute("pagina",pagina);
		if(validacion.hasErrors()) {
			System.out.println("alumnoInfo "+ alumnoService.encontrarAlumnoPorDni(dni));
			System.out.println("docsAlumnoList "+ alumnoService.encontrarDocsAlumnoListPorDni(dni));
			model.addAttribute("alumnoInfo", alumnoService.encontrarAlumnoPorDni(dni));
			model.addAttribute("docsAlumnoList", alumnoService.encontrarDocsAlumnoListPorDni(dni));
			return "list-documentacion";
		}
		
		try {
			Usuario usuarioActivo = (Usuario) model.getAttribute("usuario");
			alumnoService.addDocAlumnoEdit(docAlumnoEdit, usuarioActivo.getNickname());
			model.addAttribute("alumnoInfo", alumnoService.encontrarAlumnoPorDni(dni));
			model.addAttribute("docsAlumnoList", alumnoService.encontrarDocsAlumnoListPorDni(dni));
			System.out.println("usuarioActivo "+usuarioActivo.getNickname());
			System.out.println("docAlumnoEdit "+docAlumnoEdit.getDni());

		}catch(Exception e) {
			errores=e.getMessage();
			System.out.println(errores);
			model.addAttribute("errores", e.getMessage());
		}
		return "list-documentacion";
	}
	
	@RequestMapping(value = "/descargar-docAlumno/{dni}/{idDoc}" , method = RequestMethod.GET)
	public @ResponseBody String descargarDocAlumno(HttpServletResponse response,@PathVariable("dni") String dni,
			@PathVariable("idDoc") Integer idDoc) throws IOException{
			
		try {
			Optional<Documentacion> docAlumno = alumnoService.encontrarDocAlumnoPorDni_IdDoc(dni,idDoc);
			if(docAlumno.isPresent()) {
				String nombreFichero=dni+"_idDoc_"+idDoc+"."+docAlumno.get();
				System.out.println("docAlumno get "+docAlumno.get().getTipoFichero());
				FileSystemResource resource = fileService.getDocumentoAlumno(nombreFichero);
				System.out.println("resource get "+resource);
				//if(!resource.exists()) {
					//throw new IOException("El documento con el dni "+dni+" y el id "+idDoc+" no existe.");
				//}
				System.out.println("fichero  "+resource.getFile());
				File fichero= resource.getFile();
				response.setContentType(docAlumno.get().getContentTypeFichero());
				response.setHeader("Content-Disposition", "attachment; filename="+ fichero.getName());
				response.setHeader("Content-Length", String.valueOf(fichero.length()));
				System.out.println("fichero input "+ fichero);
				InputStream in = new FileInputStream(fichero);
				FileCopyUtils.copy(in, response.getOutputStream());
				return "redirect:list-documentacion";
			} else {
				throw new IOException ("El documento con el dni "+dni+" y el id "+idDoc+" no existe.");
			}
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
}