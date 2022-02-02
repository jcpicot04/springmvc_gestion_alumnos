package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram;

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


/**
 * @author alumno
 *
 */
public class Alumno implements Serializable, Modificable<Alumno>,Comparable<Alumno> {
	
	private static final long serialVersionUID= 1L;
	@Pattern(regexp = "[0-9]{8}[A-Za-z]{1}", message = "El dni debe tener 8 números y una letra")
	private String dni;
	@Size(min=5,message="El nombre debe de tener un tamaño mínimo de 5 carácteres")
	private String nombre;
	@NotNull(message = "La edad no puede estar vacia")
	@Range(min = 18, max = 99, message = "La edad debe ser igual o mayor a 18 y menor o igual a 99")
	@Digits(integer= 2,fraction = 0, message = "La edad no puede tener decimales ni más de 2 dígitos")
	private Integer edad;
	@Size(min=3,message="El ciclo debe tener almenos 3 carácteres")
	private String ciclo;
	@NotNull(message = "El curso no puede estar vacio")
	@Digits(fraction = 0, integer = 1,message = "El curso tiene un formato incorrecto")
	@Range(min = 1, max = 2, message = "El curso solo admite los valores 1 o 2")
	private Integer curso;
	private Date ts;
	private String user;
	private String hobbies;
	
	private ArrayList<Documentacion> docs;
	
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	private boolean erasmus;
	public boolean isErasmus() {
		return erasmus;
	}
	public void setErasmus(boolean erasmus) {
		this.erasmus = erasmus;
	}
	public String[] getInteresadoEn() {
		return interesadoEn;
	}
	public void setInteresadoEn(String[] interesadoEn) {
		this.interesadoEn = interesadoEn;
	}
	public String getLenguajeFavorito() {
		return lenguajeFavorito;
	}
	public void setLenguajeFavorito(String lenguajeFavorito) {
		this.lenguajeFavorito = lenguajeFavorito;
	}
	private ArrayList<Integer> matriculadoEn;
	
	
	public ArrayList<Integer> getMatriculadoEn() {
		return matriculadoEn;
	}
	public void setMatriculadoEn(ArrayList<Integer> matriculadoEn) {
		this.matriculadoEn = matriculadoEn;
	}
	private String[] interesadoEn;
	private String genero;
	private String lenguajeFavorito;
	private String horario;
	private String pais;


	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	//Constructores
	public Alumno(String nombre, String dni, Integer edad, String ciclo, Integer curso, boolean erasmus, String genero,
			String horario, String pais, ArrayList<Integer> matriculadoEn, String hobbies) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.edad = edad;
		this.ciclo = ciclo;
		this.curso = curso;
		this.erasmus = erasmus;
		this.genero = genero;
		this.horario = horario;
		this.pais = pais;
		this.matriculadoEn = matriculadoEn;
		this.hobbies = hobbies;
		this.setDocs(new ArrayList<Documentacion>());
	}
	public Alumno() {
		
	}
	
	public Alumno(String dni) {
		this.dni = dni;
	}
	//END
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public int getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + "]";
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
		Alumno other = (Alumno) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	public int compareTo(Alumno arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Date getTs() {
		return ts;
	}
	@Override
	public void setTs(Date ts) {
		this.ts = ts;
	}
	@Override
	public String getUser() {
		return user;
	}
	@Override
	public void setUser(String user) {
		this.user = user;
	}
	@Override
	public boolean sePuedeModificarUtilizando(Alumno itemModificado) {
		if (this.getUser() != null && this.getTs() != null) {
			String usuarioActual = this.getUser();
			String usuarioModificado = itemModificado.getUser();
			Date fechaActual = Ts.parseIso(Ts.formatIso(this.getTs()));
			Date fechaModificada = Ts.parseIso(Ts.formatIso(itemModificado.getTs()));
			if (usuarioActual.contentEquals(usuarioModificado) || !fechaActual.equals(fechaModificada))
				return false;
		}
		return true;
	}
	
	public String check() {
        if(this.erasmus) {
             return "checked";
        }
        return "";
    }

	@Override
	public String mensajeNoSePuedeModificar() {
		String msg="\r\n\t[ERROR]\r\n<br/>" +
				"\t'$item' ha sido modificado por otro usuario.\r\n<br/>" +
				"\tPara evitar la pérdida de información se impide guardar '$item'.\r\n<br/>" +
				"\tÚltima modificación realizada por [" + this.getUser() + "] el [" + Ts.ts(this.getTs()) + "]\r\n<br/>";
		return msg.replace("$item", "Alumno");
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public ArrayList<Documentacion> getDocs() {
		return docs;
	}
	public void setDocs(ArrayList<Documentacion> docs) {
		this.docs = docs;
	}

}