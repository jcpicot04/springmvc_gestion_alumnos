package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.interfaces;

import java.util.Date;

public interface Modificable <AnyType>{
	
	public Date getTs();
	
	public String getUser();
	
	public void setTs(Date ts);
	
	public void setUser(String user);
	
	public boolean sePuedeModificarUtilizando(AnyType itemModificado);
	
	public String mensajeNoSePuedeModificar();
}