package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.validaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv.I18nService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class ValidadorImagenes implements ConstraintValidator<ImagenValida, MultipartFile>{
public static final List<String> tiposDeimagenes= Arrays.asList("image/png","image/jpg","image/jpeg","image/gif");
public static final long MAX_BYTES= 524288;
@Autowired
I18nService language = new I18nService();
@Override
public void initialize(ImagenValida constrainAnnotation) {
	
}

@Override
public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
		
	boolean result = true;
	
	ArrayList<String> listaErrores= (ArrayList<String>) language.getTraduccion(mensajesErrorImagen(multipartFile));
	if(!listaErrores.isEmpty()) {
		context.disableDefaultConstraintViolation();
		for(String textoError:listaErrores) {
			context.buildConstraintViolationWithTemplate(textoError).addConstraintViolation();
		}
		result = false;
	}
	
	return result;
	}
public static ArrayList<String> mensajesErrorImagen(MultipartFile fichero){
		
	ArrayList<String> errores=new ArrayList<String>();
	
	if(fichero.isEmpty()) {
		errores.add("imagenValida.vacia");
	}
	
	String contentType = fichero.getContentType();
	if(!tipoDeImagenValido(contentType)) {
		errores.add("imagenValida.tipoIncorrecto");
	}
	
	if(fichero.getSize()>MAX_BYTES) {
		errores.add("imagenValida.tamanyoIncorrecto#"+MAX_BYTES);
	}
	return errores;
}
private static boolean tipoDeImagenValido(String contentType) {
	if(tiposDeimagenes.contains(contentType)) {
		return true;
	}else {
		return false;
	}
	}

public static boolean imagenValida(MultipartFile fichero) {
	ArrayList<String> listaErrores=mensajesErrorImagen(fichero);
	return listaErrores.isEmpty()?true:false;
}
public static String getExtension(MultipartFile fichero) {
	String extension="";
	if(tiposDeimagenes.contains(fichero.getContentType())) {
		extension = FilenameUtils.getExtension(fichero.getOriginalFilename());
		//fichero.getOriginalFilename().split(".")[1];
		return extension;
		}else {
			return "";
		}
	}
	}
