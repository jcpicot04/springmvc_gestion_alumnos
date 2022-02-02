package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order;

import java.util.Comparator;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Modulo;

public class CompararNombre implements Comparator<Modulo> {

	@Override
	public int compare(Modulo m1, Modulo m2) {
		return m1.getNombre().compareTo(m2.getNombre());
	}

}
