package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.validaciones.DocumentoAlumnoValido;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;


public class Documentacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Pattern(regexp = "[0-9]{8}[A-Za-z]{1}", message = "El dni debe tener 8 números y una letra")
	String dni;
	@NotNull(message = "El id no puede estar vacío")
	Integer id;
	

	@NotEmpty(message = "El tipo no puede estar vacío")
	String tipo;
	@Length(min = 10, message = "El comentario debe tener almenos 10 carácteres")
	String comentario;
	@DocumentoAlumnoValido
	MultipartFile fichero;
	
	String tipoFichero;
	
	String contentTypeFichero;
	
	public Documentacion() {}
	
	public Documentacion(String dni, Integer id, String tipo, String comentario) {
		this.dni = dni;
		this.id = id;
		this.tipo = tipo;
		this.comentario = comentario;
	}

	public Documentacion(String dni, Integer id, String tipo, String comentario, MultipartFile fichero) {
		this.dni = dni;
		this.id = id;
		this.tipo = tipo;
		this.comentario = comentario;
		this.fichero = fichero;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public MultipartFile getFichero() {
		return fichero;
	}

	public void setFichero(MultipartFile fichero) {
		this.fichero = fichero;
	}

	public String getTipoFichero() {
		return tipoFichero;
	}

	public void setTipoFichero(String tipoFichero) {
		this.tipoFichero = tipoFichero;
	}

	public String getContentTypeFichero() {
		return contentTypeFichero;
	}

	public void setContentTypeFichero(String contentTypeFichero) {
		this.contentTypeFichero = contentTypeFichero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Documentacion other = (Documentacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
