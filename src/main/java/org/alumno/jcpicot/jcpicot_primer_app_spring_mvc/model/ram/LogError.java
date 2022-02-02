package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram;

public class LogError implements Comparable<LogError>{
	public int id;
	private String tipo;
	private String info;
	
	public LogError(int id,String tipo, String info) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.info = info;
		;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	public int compareTo(LogError e1) {
		// TODO Auto-generated method stub
		return this.id - e1.getId();
	}
	
}