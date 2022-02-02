package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram;

public class Modulo {

	private int id;
	private String nombre;
	private String abreviatura;
	private int horas;
	
	public Modulo(int id, String nombre, String abreviatura, int horas) {
		this.id = id;
		this.nombre = nombre;
		this.abreviatura = abreviatura;
		this.horas = horas;
	}
	
	public Modulo(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Modulo other = (Modulo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
}
