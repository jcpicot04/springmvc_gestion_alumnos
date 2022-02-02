package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order;

import java.util.Comparator;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Alumno;



public class ComparadorAlumnoGeneroNombre implements Comparator<Alumno>{

	public int compare(Alumno a1, Alumno a2) {
		int comparaGenero = a1.getGenero().compareTo(a2.getGenero());
		
		if (comparaGenero!=0) {
			return comparaGenero;
		} else {
			return a1.getNombre().compareTo(a2.getNombre());
		}
		
	}

}