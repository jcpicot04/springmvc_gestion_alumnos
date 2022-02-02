package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.validaciones;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidadorDocumentoAlumno.class})
public @interface DocumentoAlumnoValido{
	String message() default "Documento incorrecto";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}