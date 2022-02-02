package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.excepciones;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Alumno;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.AlumnoEdit;

public class AlumnoDuplicadoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AlumnoEdit nuevo;
	private Alumno existente;

	public AlumnoDuplicadoException(Alumno existente, AlumnoEdit nuevo) {
			this.nuevo = nuevo;
			this.existente = existente;
	}
	
	public AlumnoDuplicadoException(AlumnoEdit nuevo) {
		this.nuevo = nuevo;
}

	@Override
	public String toString() {
		return "El alumno que quiere introducir: " + nuevo.getNombre() + " tiene un DNI ya existente " + nuevo.getDni() 
		+ ".  = " + existente.getDni() + " " + existente.getNombre() ;
	}
	
	
	
	
	
}