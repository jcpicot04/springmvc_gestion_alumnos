package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order;

import java.util.Comparator;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Alumno;



public class ComparadorAlumnoCicloNombre implements Comparator<Alumno> {

	public int compare(Alumno a1, Alumno a2) {
		
		int comparaCiclo = a1.getCiclo().compareTo(a2.getCiclo());
		if (comparaCiclo!=0) {
			return comparaCiclo;
		} else {
			return a1.getNombre().compareTo(a2.getNombre());
		}
	}

}