package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumnoInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String dni_alumno;
	private String nombre_alumno;
	private String ciclo_alumno;
	private Integer curso_alumno;
	
	public AlumnoInfo() {
	}
}






