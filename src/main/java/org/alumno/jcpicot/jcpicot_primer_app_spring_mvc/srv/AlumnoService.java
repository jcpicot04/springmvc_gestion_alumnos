package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Alumno;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Documentacion;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.FiltroAlumno;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.AlumnoEdit;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.AlumnoList;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.DocAlumnoEdit;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.DocAlumnoList;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order.ComparadorAlumnoCicloNombre;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order.ComparadorAlumnoCursoNombre;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order.ComparadorAlumnoDni;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order.ComparadorAlumnoEdadNombre;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order.ComparadorAlumnoGeneroNombre;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order.CompararNombre;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.excepciones.AlumnoDuplicadoException;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.mapper.AlumnoMapper;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class AlumnoService{
	private static ArrayList<Alumno> alumnos= new ArrayList<Alumno>();
	
	static {
		ArrayList<Integer> m = new ArrayList<Integer>();
		m.add(0);
		alumnos.add(new Alumno("Antonio", "10101010Y", 22, "DAW", 1, true, "Hombre", "Manyana", "ES", m,
				"Juego a baloncesto"));
		alumnos.add(new Alumno("Marta", "20764532A", 18, "DAW", 1, false, "Mujer", "Tarde", "ES", m,
				"Corro en maratones"));
		alumnos.add(new Alumno("Carlos", "20144647T", 26, "DAW", 1, true, "Hombre", "Tarde", "ES", m,
				"Juego a videojuegos"));
		alumnos.add(new Alumno("Pepe", "20456595G", 24, "DAW", 2, false, "Hombre", "Tarde", "ES", m,
				"Construyo maquetas de aviones"));
		
	}
	@Autowired
	FileService fileService;
	@Autowired
	I18nService i18nService;
	/*public List<Alumno> listar(String criterioOrdenacion) {
		
		switch(criterioOrdenacion) {
		case "dni":
			Collections.sort(alumnos,new ComparadorAlumnoDni());
			return alumnos;
			
		case "edad":
			Collections.sort(alumnos,new ComparadorAlumnoEdadNombre());
			return alumnos;
			
		case "ciclo":
			Collections.sort(alumnos,new ComparadorAlumnoCicloNombre());
			return alumnos;
			
		case "curso":
			Collections.sort(alumnos,new ComparadorAlumnoCursoNombre());
			return alumnos;
			
		default: Collections.sort(alumnos, new ComparadorAlumnoDni());	
			return alumnos;

		}
	}*/	

	
	public void addAlumno(AlumnoEdit alumnoEdit) throws AlumnoDuplicadoException{
		if(existeAlumno(alumnoEdit.getDni())) {
			throw new AlumnoDuplicadoException(encontrarAlumnoPorDni(alumnoEdit.getDni()),alumnoEdit);
		}else {
			alumnos.add(AlumnoMapper.INSTANCE.alumnoEditToAlumno(alumnoEdit));
		}
		
	}
	
	public boolean existeAlumno(String dni) {
		Optional<String> dniAlumno= alumnos.stream().map(a->a.getDni()).filter(id->id.equals(dni)).findFirst();
		if(dniAlumno.isPresent()) {
			return true;
		}
		return false;
	}
	
	public void addAlumnoDoc(Documentacion nuevo) {
		Optional<Alumno> alumno = alumnos.stream().filter(x -> x.getDni().equals(nuevo.getDni())).findFirst();
		if (alumno.isPresent())
			alumno.get().getDocs().add(nuevo);
	}
	
	public void addDocAlumnoEdit(DocAlumnoEdit docAlumnoEdit, String usuarioModificacion) throws Exception{
		if(docAlumnoEdit == null) {
			throw new Exception("No se ha podido actualizar el documento porque no han llegado los datos modificados");
		}else {
			String dni = (String) docAlumnoEdit.getDni();
			Alumno alumno = encontrarAlumnoPorDni(dni);
			if (alumno == null) throw new Exception("Alumno desconocido");
			if (usuarioModificacion == null) throw new Exception("Para añadir documentación debe estar logeado");
			
			//Guardar fichero en el Sistema Operativo
			//1º Componer nombre del fichero
			String extension = fileService.getExtensionMultipartFile(docAlumnoEdit.getFichero());
			String nombreFicheroAGuardar = String.format("%s_idDoc_%s.%s", dni,docAlumnoEdit.getId(),extension);
			//2º Guardar fichero en la carpeta:Si no se ha podido listaErroresAlGuardar no estará vacío
			ArrayList<String> listaErroresAlGuardar = fileService.guardaDocumentoAlumno(docAlumnoEdit.getFichero(), nombreFicheroAGuardar);
			if(!listaErroresAlGuardar.isEmpty()) {
				String mensajeCompleto = "";
				for(String mensaje : listaErroresAlGuardar)
					mensajeCompleto += i18nService.getTraduccion(mensaje) + "<br>";
				throw new Exception(mensajeCompleto);
			}
			
			docAlumnoEdit.setTipoFichero(extension);
			docAlumnoEdit.setContentTypeFichero(docAlumnoEdit.getFichero().getContentType());
			// 3º Añadir el nuevo documento al alumno
			// Añadir el docAlumno
			Documentacion docAlumno = AlumnoMapper.INSTANCE.docAlumnoEditToDocAlumno(docAlumnoEdit);
			if (alumno.getDocs() != null)//lista de documentos no vacía
				alumno.getDocs().add(docAlumno);
			else {
				alumno.setDocs(new ArrayList<Documentacion>(List.of(docAlumno)));
			}
			//4º Al modificar el alumno (su lista de docs) debemos actualizar la fecha y usuario
			modificaAlumno(AlumnoMapper.INSTANCE.alumnoToAlumnoEdit(alumno),usuarioModificacion);
		}
	}
	
	public void delAlumno(Alumno alumno) {
			alumnos.remove(alumno);
			
		}
	
	public AlumnoEdit validar(AlumnoEdit nuevo) throws AlumnoDuplicadoException {
		if(alumnos.contains(nuevo)) {
			throw new AlumnoDuplicadoException(repetido(nuevo),nuevo);
		}
		return nuevo;
	}
	
	public Alumno repetido(AlumnoEdit alumno) {
		Iterator<Alumno> it = alumnos.iterator();
		Alumno lista = new Alumno("");
		
		while(it.hasNext()) {
			lista = it.next();
			if (lista.equals(alumno)) {
				return lista;
			}
		}
		return lista;
	}
	
	public Alumno encontrarAlumnoPorDni(String dni) {

		Optional<Alumno> alumno = alumnos.stream().filter(a -> a.getDni().equals(dni)).findFirst();
		if (alumno.isPresent())
			return alumno.get();
		return null;
	}
	
	public int siguienteDoc(String dni){
		
		int idFinal=0;
		
		if(encontrarAlumnoPorDni(dni).getDocs().size() == 0)
			return ++idFinal;
		
		for(Documentacion docAlumnoEdit : encontrarAlumnoPorDni(dni).getDocs()) {
			idFinal = docAlumnoEdit.getId();
		}
		
		return ++idFinal;
	}
	
	public ArrayList<DocAlumnoList> encontrarDocsAlumnoListPorDni(String dni){
		ArrayList<DocAlumnoList> resultado = new ArrayList<DocAlumnoList>();
		Alumno alumno = encontrarAlumnoPorDni(dni);
		if(alumno == null)
			return resultado;
		else {
			if(alumno.getDocs() == null || alumno.getDocs().size() == 0)
				return resultado;
			else {
				return AlumnoMapper.INSTANCE.docsAlumnoToDocsAlumnoList(alumno.getDocs());
			}
		}
	}
	
	public Optional<Documentacion> encontrarDocAlumnoPorDni_IdDoc(String dni, int idDoc) {
		Optional<Alumno> alumno = alumnos.stream().filter(a -> a.getDni().equals(dni)).findFirst();
		if (alumno.isPresent()) {
			Alumno alumnoDoc = alumno.get();
			Optional<Documentacion> doc = alumnoDoc.getDocs().stream().filter(b -> b.getId()==idDoc).findFirst();
			if(doc.isPresent())
				System.out.println("doc encontraralumno "+doc);
			return doc;
		}
		return null;
	}
	
	public void modificaAlumno(AlumnoEdit alumnoModificado, String usuarioModificado) throws Exception {
	String errores="";
	if(alumnoModificado == null) {
		errores="No se ha podido actualizar el alumno porque no han llegado los datos modificados";
	}else {
		Alumno alumno=encontrarAlumnoPorDni(alumnoModificado.getDni());
		AlumnoEdit alumnoEditActual = AlumnoMapper.INSTANCE.alumnoToAlumnoEdit(alumno);
		if(alumnoEditActual.sePuedeModificarUtilizando(alumnoModificado)) {
			alumnoModificado.setUser(usuarioModificado);
			alumnoModificado.setTs(new Date());
			AlumnoMapper.INSTANCE.updateAlumnoFromAlumnoEdit(alumnoModificado, alumno);		
	}else {
		errores= alumno.mensajeNoSePuedeModificar();
	}
	}
		if(errores.length()>0) {
			throw new Exception(errores);
		}
		
		/*Alumno original = encontrarAlumnoPorDni(alumnoModificado.getDni());
	System.out.println("originalentra");
	if(original.sePuedeModificarUtilizando(alumnoModificado)) {
		alumnos.remove(original);
		alumnoModificado.setUser(usuarioModificado);
		Date fecha = new Date();
		alumnoModificado.setTs(fecha);
		alumnos.add(alumnoModificado);
		}else {
			System.out.println("throw");
			throw new Exception(original.mensajeNoSePuedeModificar());
	}*/
	
	}
	



	public List<String> listaInteresadoEn(){
		ArrayList listaint = new ArrayList();
		listaint.add("Backend");
		listaint.add("Frontend");
		return listaint;
		
	}
	public List<String> listaGenero(){
		ArrayList listagen = new ArrayList();
		listagen.add("Hombre");
		listagen.add("Mujer");
		return listagen;
		
	}
	
	public List<String> listaHorario(){
		ArrayList listhorario = new ArrayList();
		listhorario.add("Manyana");
		listhorario.add("Tarde");
		return listhorario;
		
	}
	public HashMap<String,String> listaPais(){
		HashMap<String, String> listpais = new HashMap<String, String>();
		listpais.put("ES","Espanya");
		listpais.put("USA","EEUU");
		listpais.put("BRA","Brasil");
		listpais.put("FRA", "Francia");
		return listpais;
		
	}
	public List<Alumno> listaAlumnos() {
		return alumnos;
	}
	public ArrayList<AlumnoList> listaAlumnos(String criterio) {
		switch (criterio) {
		case "ciclo":
			Collections.sort(alumnos, new ComparadorAlumnoCicloNombre());
		case "curso":
			Collections.sort(alumnos, new ComparadorAlumnoCursoNombre());
			break;
		case "dni":
			Collections.sort(alumnos, new ComparadorAlumnoDni());
			break;
		case "edad":
			Collections.sort(alumnos, new ComparadorAlumnoEdadNombre());
			break;
		case "nombre":
			Collections.sort(alumnos);
			break;
		case "pais":
			Collections.sort(alumnos, new ComparadorAlumnoDni());

		case "Genero":
			Collections.sort(alumnos, new ComparadorAlumnoGeneroNombre());
			break;
		}
		return AlumnoMapper.INSTANCE.alumnosToAlumnosList(alumnos);
	}

	public List<Alumno> filter(FiltroAlumno falumno) {
		try {
			switch (falumno.getType()) {
			case "dni":
				return falumno.filterByDni(falumno.getValue(), AlumnoService.alumnos);
			case "edad":
				return falumno.filterByEdad(Integer.parseInt(falumno.getValue()), AlumnoService.alumnos);
			case "pais":
				return falumno.filterByPais(falumno.getValue(), AlumnoService.alumnos);
			case "horario":
				return falumno.filterByHorario(falumno.getValue(), AlumnoService.alumnos);
			case "curso":
				return falumno.filterByCurso(Integer.parseInt(falumno.getValue()), AlumnoService.alumnos);
			case "modulo":
				return falumno.filterByCiclo(falumno.getValue(), AlumnoService.alumnos);
			}
		} catch (Exception e) {
			return AlumnoService.alumnos;
		}
		return AlumnoService.alumnos;
	}
	private static HashMap<String, String> searchLista = new HashMap<String, String>();
	public HashMap<String, String> listaSearch() {
		searchLista.put("dni", "Dni");
		searchLista.put("edad", "Edad");
		searchLista.put("pais", "Pais");
		searchLista.put("horario", "Horario");
		searchLista.put("curso", "Curso");
		searchLista.put("modulo", "Modulo");
		return searchLista;
	}
	
	
	public List<String> listaTipo(){
		ArrayList listatip = new ArrayList();
		listatip.add("Certificado");
		listatip.add("Justificante");
		listatip.add("Solicitud");

		return listatip;
		
	}


}