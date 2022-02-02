package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Pagina;
import org.springframework.stereotype.Service;
@Service
public class PaginaService {
	
	private static Pagina pagina = new Pagina("Login","Home");

	public Pagina getPagina() {
		return pagina;
	}

	public void setPagina(Pagina nuevaPagina) {
		pagina = nuevaPagina;
	}
	
	

}