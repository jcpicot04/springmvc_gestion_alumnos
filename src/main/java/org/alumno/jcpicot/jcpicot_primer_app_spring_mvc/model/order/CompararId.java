package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order;

import java.util.Comparator;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Modulo;

public class CompararId implements Comparator<Modulo>{

	public int compare(Modulo m1, Modulo m2) {
		return m1.getId()-m2.getId();
	}



}
