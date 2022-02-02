package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order;

import java.util.Comparator;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.LogError;


public class ComparadorLogErrorExplicacion implements Comparator<LogError> {

	
	public int compare(LogError e1, LogError e2) {
		// TODO Auto-generated method stub
		return e1.getInfo().compareTo(e2.getInfo());
	}
	
}