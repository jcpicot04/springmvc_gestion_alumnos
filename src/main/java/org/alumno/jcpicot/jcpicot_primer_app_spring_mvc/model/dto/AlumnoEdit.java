package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.interfaces.Modificable;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.util.Ts;
import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumnoEdit implements Modificable<AlumnoEdit>, Serializable{
	private static final long serialVersionUID = 1L;
	@Pattern(regexp = "[0-9]{8}[A-Za-z]{1}", message = "El dni debe tener 8 números y una letra")
	private String dni;
	@Size(min=5,message="El nombre debe de tener un tamaño mínimo de 5 carácteres")
	private String nombre;
	@NotNull(message = "La edad no puede estar vacia")
	@Range(min = 18, max = 99, message = "La edad debe ser igual o mayor a 18 y menor o igual a 99")
	@Digits(integer = 2,fraction = 0, message = "La edad no puede tener decimales ni más de 2 dígitos")
	private Integer edad;
	@Size(min = 3, message = "El ciclo debe tener almenos 3 carácteres")
	private String ciclo;
	@NotNull(message = "El curso no puede estar vacio")
	@Digits(fraction = 0, integer = 1,message = "El curso tiene un formato incorrecto")
	@Range(min = 1, max = 2, message = "El curso solo admite los valores 1 o 2")
	private Integer curso;
	
	private boolean erasmus=false;
	private String[] interesadoEn;
	private String lenguajeFavorito="";
	private String genero;
	private String horario;
	private String pais;
	private ArrayList<Integer> matriculadoEn;
	private String hobbies;
	private Date ts;
	private String user;

	public AlumnoEdit() {
	}

	public AlumnoEdit(String dni) {
		super();
		this.dni = dni;
	}

	public AlumnoEdit(String dni, String nombre, Integer edad, String ciclo, Integer curso) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
		this.ciclo = ciclo;
		this.curso = curso;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlumnoEdit other = (AlumnoEdit) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + "]";
	}

	public boolean sePuedeModificarUtilizando(AlumnoEdit itemModificado) {
		if (this.getUser() != null && this.getTs()!= null) {
			//Existe un usuario y una fecha Inicial y tenemos que comprobar
			String usuarioActual = this.getUser();
			String usuarioModificado = itemModificado.getUser();
			//formateamos fechas gracias a la clase Ts que formatea fechas
			Date fechaActual = Ts.parseIso(Ts.formatIso(this.getTs()));
			Date fechaModificada = Ts.parseIso(Ts.formatIso(itemModificado.getTs()));
			if (!usuarioActual.equals(usuarioModificado) || !fechaActual.equals(fechaModificada))
				//El usuario no es el mismo o la fecha cambia
				return false;
		}
		//No tenemos fecha o usuario-> 1º modificación, por lo que se puede modificar
		return true;
	}

	
	public String mensajeNoSePuedeModificar() {
		//Mensaje genérico para poder reutilizarlo
		String msg="\r\n\t[ERROR]\r\n<br/>" +
				"\t'$item' ha sido modificado por otro usuario.\r\n<br/>" +
				"\tPara evitar la pérdida de información se impide guardar '$item'.\r\n<br/>" +
				"\tÚltima modificación realizada por [" + this.getUser() + "] el [" + 
				Ts.ts(this.getTs()) + "]\r\n<br/>";
		//Para concretar el tipo de registro modificado sustituimos $item por Alumno
		return msg.replace("$item", "Alumno");
	}


}






