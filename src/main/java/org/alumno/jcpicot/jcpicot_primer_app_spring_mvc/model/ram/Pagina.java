package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Pagina;

public class Pagina {
	String titulo;
	String paginaActiva;
    String idioma;
	
	public Pagina(String titulo, String paginaActiva) {
		this.titulo = titulo;
		this.paginaActiva = paginaActiva;
		this.idioma="es";
	}
	public Pagina(String titulo, String paginaActiva,String idioma) {
		this.titulo = titulo;
		this.paginaActiva = paginaActiva;
		this.idioma=idioma;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paginaActiva == null) ? 0 : paginaActiva.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Pagina other = (Pagina) obj;
		if (paginaActiva == null) {
			if (other.paginaActiva != null)
				return false;
		} else if (!paginaActiva.equals(other.paginaActiva))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getPaginaActiva() {
		return paginaActiva;
	}
	public void setPaginaActiva(String paginaActiva) {
		this.paginaActiva = paginaActiva;
	}
	
	public String getStrBootstrapActiva(String pagina) {
		if (paginaActiva.equals(pagina)) {
			return "active";
		} 
		else {
			return "";
		}
	}

}