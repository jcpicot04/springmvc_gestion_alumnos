package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Modulo;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Alumno;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order.CompararAbreviatura;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order.CompararHoras;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order.CompararId;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.order.CompararNombre;
import org.springframework.stereotype.Service;


@Service
public class ModuloService {
	
private static List<Modulo> modulos= new ArrayList<Modulo>();
	
	static {
		modulos.add(new Modulo(1,"Programacion","PRO",8));
		modulos.add(new Modulo(2,"Base de Dades","BDD",8));
	}
	
public List<Modulo> listar(String orden) {
	
	switch(orden) {
	case "id":
		Collections.sort(modulos, new CompararId());
		return modulos;
	case "nombre":
		Collections.sort(modulos, new CompararNombre());
		return modulos;
	case "abreviatura":
		Collections.sort(modulos, new CompararAbreviatura());
		return modulos;
	case "horas":
		Collections.sort(modulos, new CompararHoras());
		return modulos;
	}
	return modulos;
}

public Modulo igual(Modulo repetido) {
	Iterator<Modulo> it = modulos.iterator();
	Modulo existente = new Modulo (0);
	while(it.hasNext()) {
		existente = it.next();
		if(existente.equals(repetido))
			return existente;
	}
	return null;
}
public HashMap<Integer,Modulo> listaMatriculado(){
	HashMap<Integer,Modulo> listamat = new HashMap<Integer,Modulo>();
	for(Modulo modulo : modulos)
		listamat.put(modulo.getId(), modulo);
	return listamat;
}

public void addModulo(Modulo modulo) {
	modulos.add(modulo);
	
}

public void delModulo(Modulo modulo) {
		modulos.remove(modulo);
		
	}

public String validar(Modulo nuevo) {
	if(modulos.contains(nuevo)) {
		return "El módulo ya existe.";
	}
	return "";
}

public String comprobarIntetnos(int intentos) {
	if(intentos >= 3)
		return " Se han introducido 3 módulos en la misma sesión.";
	return "";
}


public String comprobar(String id, String nombre, String abreviatura, String horas) {
	
	String error = "";
	
	//Comprovar ID
	try {
		Integer.parseInt(id);
	}
	catch(Exception e) {
		error = "El id no puede contener letras. ";
	}
	
	//Comprovar Nombre
	if(nombre=="")
		error += "El campo nombre no puede estar vacío. ";
	
	if(nombre.length()>5)
		error += "El nombre debe contener mínimo 5 caracteres. ";
	
	//Comprovar horas
	try {
		int h = Integer.parseInt(horas);
		if(h<2)
			error += "Introduce más de 2 horas. ";
	}
	catch(Exception e) {
		error += "Introduce un valor numérico. ";
	}
	
	//Comprovar abreviatura
	if(abreviatura == "")
		error += "El campo no puede estar vacio. ";
	
	return error;
}
}	

