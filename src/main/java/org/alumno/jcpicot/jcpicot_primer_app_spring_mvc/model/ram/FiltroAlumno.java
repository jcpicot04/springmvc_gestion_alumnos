package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroAlumno {

	private String type;
	private String value;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public FiltroAlumno() {
	}

	public FiltroAlumno(String type, String value) {
		this.type = type;
		this.value = value;
	}

	public List<Alumno> filterByDni(String dni, List<Alumno> alumnos) {
		return alumnos.stream().filter(a -> a.getDni().equals(dni)).collect(Collectors.toList());

	}

	public List<Alumno> filterByEdad(int edad, List<Alumno> alumnos) {
		return alumnos.stream().filter(a -> a.getEdad() == edad).collect(Collectors.toList());

	}

	public List<Alumno> filterByCiclo(String ciclo, List<Alumno> alumnos) {
		return alumnos.stream().filter(a -> a.getCiclo().equals(ciclo)).collect(Collectors.toList());
	}

	public List<Alumno> filterByHorario(String horario, List<Alumno> alumnos) {
		return alumnos.stream().filter(a -> a.getHorario().equals(horario)).collect(Collectors.toList());
	}

	public List<Alumno> filterByPais(String pais, List<Alumno> alumnos) {
		return alumnos.stream().filter(a -> a.getPais().equals(pais)).collect(Collectors.toList());
	}

	public List<Alumno> filterByCurso(int curso, List<Alumno> alumnos) {
		return alumnos.stream().filter(a -> a.getCurso() == curso).collect(Collectors.toList());
	}

}