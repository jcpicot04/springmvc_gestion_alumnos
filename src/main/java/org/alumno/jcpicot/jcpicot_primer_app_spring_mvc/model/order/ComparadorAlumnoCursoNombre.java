package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order;

import java.util.Comparator;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Alumno;



public class ComparadorAlumnoCursoNombre implements Comparator<Alumno> {

	
	public int compare(Alumno a1, Alumno a2) {
		int comparaCurso = a1.getCurso()-(a2.getCurso());
		
		if (comparaCurso!=0) {
			return comparaCurso;
		} else {
			return a1.getNombre().compareTo(a2.getNombre());
		}
	}

}