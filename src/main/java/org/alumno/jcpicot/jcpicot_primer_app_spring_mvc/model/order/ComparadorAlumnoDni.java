package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order;

import java.util.Comparator;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Alumno;


public class ComparadorAlumnoDni implements Comparator<Alumno>{

	public int compare(Alumno a1, Alumno a2) {
		return a1.getDni().compareTo(a2.getDni());
	}

}