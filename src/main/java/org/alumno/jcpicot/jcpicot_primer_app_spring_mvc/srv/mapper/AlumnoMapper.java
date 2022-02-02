package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.mapper;
import java.util.ArrayList;
import java.util.List;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Alumno;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Documentacion;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.AlumnoEdit;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.AlumnoInfo;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.AlumnoList;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.DocAlumnoEdit;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.DocAlumnoList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlumnoMapper {
	AlumnoMapper INSTANCE= Mappers.getMapper(AlumnoMapper.class);
	
	//Devuelve un objeto de tipo 'AlumnoEdit' a partir de un objeto de tipo 'Alumno'
	AlumnoEdit alumnoToAlumnoEdit(Alumno alumno);
	//Devuelve un objeto de tipo 'AlumnoList' a partir de un objeto de tipo 'Alumno'
	AlumnoList alumnoToAlumnoList(Alumno alumno);
	//Devuelve una lista de objetos 'AlumnoList' a partir de una lista de tipo 'Alumno'
	ArrayList<AlumnoList> alumnosToAlumnosList(ArrayList<Alumno> alumnos);

	@Mapping(source="dni",target="dni_alumno")
	@Mapping(source="nombre",target="nombre_alumno")
	@Mapping(source="ciclo",target="ciclo_alumno")
	@Mapping(source="curso",target="curso_alumno")
    	//Devuelve un objeto de tipo 'AlumnoInfo' a partir de un objeto de tipo 'Alumno'
	AlumnoInfo alumnoToAlumnoInfo(Alumno alumno);
    	//Devuelve un objeto de tipo 'Alumno' a partir de un objeto de tipo 'AlumnoEdit'
	Alumno alumnoEditToAlumno(AlumnoEdit alumnoEdit);
	
	//Actualiza un objeto de tipo 'Alumno' con los datos de un objeto de tipo 'AlumnoEdit'
	void updateAlumnoFromAlumnoEdit(AlumnoEdit alumnoEdit,@MappingTarget Alumno alumno);
	
	//Devuelve un objeto de tipo 'DocAlumnoEdit' a partir de un objeto de tipo 'DocAlumno'
	AlumnoEdit docAlumnoToDocAlumnoEdit(Documentacion docAlumno);
	//Devuelve una lista de objetos 'DocAlumnoList' a partir de una lista de tipo 'DocAlumno'
	ArrayList<DocAlumnoList> docsAlumnoToDocsAlumnoList(ArrayList<Documentacion> docAlumnos);
	//Devuelve un objeto de tipo 'DocAlumno' a partir de un objeto de tipo 'DocAlumnoEdit'
	Documentacion docAlumnoEditToDocAlumno(DocAlumnoEdit docAlumnoEdit);
}
