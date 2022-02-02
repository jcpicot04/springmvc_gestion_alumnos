package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.Generated;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.AlumnoEdit;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.AlumnoInfo;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.AlumnoList;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.DocAlumnoEdit;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.dto.DocAlumnoList;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Alumno;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Documentacion;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-02T19:45:09+0100",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.1 (Eclipse Adoptium)"
)
public class AlumnoMapperImpl implements AlumnoMapper {

    @Override
    public AlumnoEdit alumnoToAlumnoEdit(Alumno alumno) {
        if ( alumno == null ) {
            return null;
        }

        AlumnoEdit alumnoEdit = new AlumnoEdit();

        alumnoEdit.setCiclo( alumno.getCiclo() );
        alumnoEdit.setCurso( alumno.getCurso() );
        alumnoEdit.setDni( alumno.getDni() );
        alumnoEdit.setEdad( alumno.getEdad() );
        alumnoEdit.setErasmus( alumno.isErasmus() );
        alumnoEdit.setGenero( alumno.getGenero() );
        alumnoEdit.setHobbies( alumno.getHobbies() );
        alumnoEdit.setHorario( alumno.getHorario() );
        String[] interesadoEn = alumno.getInteresadoEn();
        if ( interesadoEn != null ) {
            alumnoEdit.setInteresadoEn( Arrays.copyOf( interesadoEn, interesadoEn.length ) );
        }
        alumnoEdit.setLenguajeFavorito( alumno.getLenguajeFavorito() );
        ArrayList<Integer> arrayList = alumno.getMatriculadoEn();
        if ( arrayList != null ) {
            alumnoEdit.setMatriculadoEn( new ArrayList<Integer>( arrayList ) );
        }
        alumnoEdit.setNombre( alumno.getNombre() );
        alumnoEdit.setPais( alumno.getPais() );
        alumnoEdit.setTs( alumno.getTs() );
        alumnoEdit.setUser( alumno.getUser() );

        return alumnoEdit;
    }

    @Override
    public AlumnoList alumnoToAlumnoList(Alumno alumno) {
        if ( alumno == null ) {
            return null;
        }

        AlumnoList alumnoList = new AlumnoList();

        alumnoList.setCiclo( alumno.getCiclo() );
        alumnoList.setCurso( alumno.getCurso() );
        alumnoList.setDni( alumno.getDni() );
        alumnoList.setEdad( alumno.getEdad() );
        alumnoList.setErasmus( alumno.isErasmus() );
        alumnoList.setNombre( alumno.getNombre() );

        return alumnoList;
    }

    @Override
    public ArrayList<AlumnoList> alumnosToAlumnosList(ArrayList<Alumno> alumnos) {
        if ( alumnos == null ) {
            return null;
        }

        ArrayList<AlumnoList> arrayList = new ArrayList<AlumnoList>();
        for ( Alumno alumno : alumnos ) {
            arrayList.add( alumnoToAlumnoList( alumno ) );
        }

        return arrayList;
    }

    @Override
    public AlumnoInfo alumnoToAlumnoInfo(Alumno alumno) {
        if ( alumno == null ) {
            return null;
        }

        AlumnoInfo alumnoInfo = new AlumnoInfo();

        alumnoInfo.setDni_alumno( alumno.getDni() );
        alumnoInfo.setNombre_alumno( alumno.getNombre() );
        alumnoInfo.setCiclo_alumno( alumno.getCiclo() );
        alumnoInfo.setCurso_alumno( alumno.getCurso() );

        return alumnoInfo;
    }

    @Override
    public Alumno alumnoEditToAlumno(AlumnoEdit alumnoEdit) {
        if ( alumnoEdit == null ) {
            return null;
        }

        Alumno alumno = new Alumno();

        alumno.setHobbies( alumnoEdit.getHobbies() );
        alumno.setErasmus( alumnoEdit.isErasmus() );
        String[] interesadoEn = alumnoEdit.getInteresadoEn();
        if ( interesadoEn != null ) {
            alumno.setInteresadoEn( Arrays.copyOf( interesadoEn, interesadoEn.length ) );
        }
        alumno.setLenguajeFavorito( alumnoEdit.getLenguajeFavorito() );
        ArrayList<Integer> arrayList = alumnoEdit.getMatriculadoEn();
        if ( arrayList != null ) {
            alumno.setMatriculadoEn( new ArrayList<Integer>( arrayList ) );
        }
        alumno.setPais( alumnoEdit.getPais() );
        alumno.setHorario( alumnoEdit.getHorario() );
        alumno.setDni( alumnoEdit.getDni() );
        alumno.setEdad( alumnoEdit.getEdad() );
        alumno.setCiclo( alumnoEdit.getCiclo() );
        alumno.setCurso( alumnoEdit.getCurso() );
        alumno.setNombre( alumnoEdit.getNombre() );
        alumno.setTs( alumnoEdit.getTs() );
        alumno.setUser( alumnoEdit.getUser() );
        alumno.setGenero( alumnoEdit.getGenero() );

        return alumno;
    }

    @Override
    public void updateAlumnoFromAlumnoEdit(AlumnoEdit alumnoEdit, Alumno alumno) {
        if ( alumnoEdit == null ) {
            return;
        }

        alumno.setHobbies( alumnoEdit.getHobbies() );
        alumno.setErasmus( alumnoEdit.isErasmus() );
        String[] interesadoEn = alumnoEdit.getInteresadoEn();
        if ( interesadoEn != null ) {
            alumno.setInteresadoEn( Arrays.copyOf( interesadoEn, interesadoEn.length ) );
        }
        else {
            alumno.setInteresadoEn( null );
        }
        alumno.setLenguajeFavorito( alumnoEdit.getLenguajeFavorito() );
        if ( alumno.getMatriculadoEn() != null ) {
            ArrayList<Integer> arrayList = alumnoEdit.getMatriculadoEn();
            if ( arrayList != null ) {
                alumno.getMatriculadoEn().clear();
                alumno.getMatriculadoEn().addAll( arrayList );
            }
            else {
                alumno.setMatriculadoEn( null );
            }
        }
        else {
            ArrayList<Integer> arrayList = alumnoEdit.getMatriculadoEn();
            if ( arrayList != null ) {
                alumno.setMatriculadoEn( new ArrayList<Integer>( arrayList ) );
            }
        }
        alumno.setPais( alumnoEdit.getPais() );
        alumno.setHorario( alumnoEdit.getHorario() );
        alumno.setDni( alumnoEdit.getDni() );
        alumno.setEdad( alumnoEdit.getEdad() );
        alumno.setCiclo( alumnoEdit.getCiclo() );
        alumno.setCurso( alumnoEdit.getCurso() );
        alumno.setNombre( alumnoEdit.getNombre() );
        alumno.setTs( alumnoEdit.getTs() );
        alumno.setUser( alumnoEdit.getUser() );
        alumno.setGenero( alumnoEdit.getGenero() );
    }

    @Override
    public AlumnoEdit docAlumnoToDocAlumnoEdit(Documentacion docAlumno) {
        if ( docAlumno == null ) {
            return null;
        }

        AlumnoEdit alumnoEdit = new AlumnoEdit();

        alumnoEdit.setDni( docAlumno.getDni() );

        return alumnoEdit;
    }

    @Override
    public ArrayList<DocAlumnoList> docsAlumnoToDocsAlumnoList(ArrayList<Documentacion> docAlumnos) {
        if ( docAlumnos == null ) {
            return null;
        }

        ArrayList<DocAlumnoList> arrayList = new ArrayList<DocAlumnoList>();
        for ( Documentacion documentacion : docAlumnos ) {
            arrayList.add( documentacionToDocAlumnoList( documentacion ) );
        }

        return arrayList;
    }

    @Override
    public Documentacion docAlumnoEditToDocAlumno(DocAlumnoEdit docAlumnoEdit) {
        if ( docAlumnoEdit == null ) {
            return null;
        }

        Documentacion documentacion = new Documentacion();

        documentacion.setDni( docAlumnoEdit.getDni() );
        documentacion.setId( docAlumnoEdit.getId() );
        documentacion.setTipo( docAlumnoEdit.getTipo() );
        documentacion.setComentario( docAlumnoEdit.getComentario() );
        documentacion.setFichero( docAlumnoEdit.getFichero() );
        documentacion.setTipoFichero( docAlumnoEdit.getTipoFichero() );
        documentacion.setContentTypeFichero( docAlumnoEdit.getContentTypeFichero() );

        return documentacion;
    }

    protected DocAlumnoList documentacionToDocAlumnoList(Documentacion documentacion) {
        if ( documentacion == null ) {
            return null;
        }

        DocAlumnoList docAlumnoList = new DocAlumnoList();

        docAlumnoList.setComentario( documentacion.getComentario() );
        docAlumnoList.setDni( documentacion.getDni() );
        docAlumnoList.setId( documentacion.getId() );
        docAlumnoList.setTipo( documentacion.getTipo() );
        docAlumnoList.setTipoFichero( documentacion.getTipoFichero() );

        return docAlumnoList;
    }
}
