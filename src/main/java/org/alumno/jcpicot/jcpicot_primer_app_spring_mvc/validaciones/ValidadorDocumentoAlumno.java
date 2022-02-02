package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.validaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class ValidadorDocumentoAlumno implements ConstraintValidator<DocumentoAlumnoValido, MultipartFile>{
public static final List<String> tiposDeDocumentos=Arrays.asList("image/png", "image/jpg", "image/jpeg",
			"image/gif","text/plain","application/pdf","application/msword","application/vnd.ms-excel",
			"application/vnd.openxmlformats-officedocument.wordprocessingml.document","application/excel",

			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
			"application/vnd.oasis.opendocument.text","application/vnd.oasis.opendocument.spreadsheet",

			"application/x-rar-compressed","application/zip",
			"application/x-zip-compressed","multipart/x-zip");
public static final long MAX_BYTES= 2097152;
@Override
public void initialize(DocumentoAlumnoValido constrainAnnotation) {
	
}

@Override
public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
		
	boolean result = true;
	
	ArrayList<String> listaErrores=mensajesErrorDocumento(multipartFile);
	if(!listaErrores.isEmpty()) {
		context.disableDefaultConstraintViolation();
		for(String textoError:listaErrores) {
			context.buildConstraintViolationWithTemplate(textoError).addConstraintViolation();
		}
		result = false;
	}
	
	return result;
	}
public static ArrayList<String> mensajesErrorDocumento(MultipartFile fichero){
		
	ArrayList<String> errores=new ArrayList<String>();
	
	if(fichero.isEmpty()) {
		errores.add("El documento no puede estar vacío");
	}
	
	String contentType = fichero.getContentType();
	if(!tipoDeDocumentoValido(contentType)) {
		errores.add("No se permiten éste tipo de documentos");
	}
	
	if(fichero.getSize()>MAX_BYTES) {
		errores.add("Tamaño máximo excedido ("+MAX_BYTES+" bytes)");
	}
	return errores;
}
private static boolean tipoDeDocumentoValido(String contentType) {
	if(tiposDeDocumentos.contains(contentType)) {
		return true;
	}else {
		return false;
	}
	}

public static boolean DocumentoValido(MultipartFile fichero) {
	ArrayList<String> listaErrores=mensajesErrorDocumento(fichero);
	return listaErrores.isEmpty()?true:false;
}

public static String getExtension(MultipartFile fichero) {
	String extension="";
	if(tiposDeDocumentos.contains(fichero.getContentType())) {
		extension = FilenameUtils.getExtension(fichero.getOriginalFilename());
		//fichero.getOriginalFilename().split(".")[1];
		return extension;
		}else {
			return "";
		}
	}

	}