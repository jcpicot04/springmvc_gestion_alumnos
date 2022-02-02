package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.validaciones.DocumentoAlumnoValido;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DocAlumnoEdit implements Comparable<DocAlumnoEdit>{
	@Pattern(regexp = "[0-9]{8}[A-Za-z]{1}", message = "El dni debe tener 8 números y una letra")
	private String dni;
	@NotNull(message = "El id no puede estar vacio")
	private Integer id;
	@NotNull(message = "El tipo no puede estar vacio")
	private String tipo;
	@Size(min = 10, message = "Los comentarios debe tener almenos 10 carácteres")
	private String comentario;
	
    //@NotNull
    @DocumentoAlumnoValido
    private MultipartFile fichero;
    
    String tipoFichero="";
    String contentTypeFichero="";
	
	
	public DocAlumnoEdit() {
		super();
	}
	public DocAlumnoEdit(Integer id) {
		super();
		this.id = id;
	}
	public DocAlumnoEdit(Integer id,String dni) {
		super();
		this.id = id;
		this.dni = dni;
	}
	@Override
	public int compareTo(DocAlumnoEdit doc) {
		return id-doc.getId();
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
		DocAlumnoEdit other = (DocAlumnoEdit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
