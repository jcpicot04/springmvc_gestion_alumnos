package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.interfaces.Modificable;

public class Usuario implements Serializable,Modificable<Usuario>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(min=5,message="El nickname debe de tener un tamaño mínimo de 5 carácteres")
	private String nickname;

	private String nombre;
	
	@Pattern(regexp="^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$",message="La password debe incluir al menos un dígito, una minuscula, una mayuscula o un carácter no alfanumerico")
	private String password;
	
	private String nombreFicheroConImagen;
	
	private Date ts;
	
	private String user;
	
	public String getNombreFicheroConImagen() {
		return nombreFicheroConImagen;
	}
	public void setNombreFicheroConImagen(String nombreFicheroConImagen) {
		this.nombreFicheroConImagen = nombreFicheroConImagen;
	}
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Usuario() {
		
	}
	public Usuario(String nickname) {
		super();
		this.nickname = nickname;
	}

	public Usuario(String nickname, String nombre, String password, String nombreFicheroConImagen) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.password = password;
		this.nombreFicheroConImagen = nombreFicheroConImagen;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
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
		Usuario other = (Usuario) obj;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		return true;
	}

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public boolean sePuedeModificarUtilizando(Usuario itemModificado) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String mensajeNoSePuedeModificar() {
		// TODO Auto-generated method stub
		return null;
	}
}