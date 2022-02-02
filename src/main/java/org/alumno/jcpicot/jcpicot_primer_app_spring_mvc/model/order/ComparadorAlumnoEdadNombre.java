package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order;

import java.util.Comparator;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Alumno;



public class ComparadorAlumnoEdadNombre implements Comparator<Alumno>{

	public int compare(Alumno a1, Alumno a2) {
		int comparaEdad = a1.getEdad()-(a2.getEdad());
		
		if (comparaEdad!=0) {
			return comparaEdad;
		} else {
			return a1.getNombre().compareTo(a2.getNombre());
		}
		
	}

}