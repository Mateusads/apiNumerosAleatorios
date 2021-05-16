package com.medeiros.apiNumerosAleatorios.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.medeiros.apiNumerosAleatorios.services.exceptions.ConstraintViolationExceptions;
import com.medeiros.apiNumerosAleatorios.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resorceNotFound(ResourceNotFoundException e, 
			HttpServletRequest request){
		String error = "Resorce not Found ";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	
	@ExceptionHandler(ConstraintViolationExceptions.class)
	public ResponseEntity<StandardError> invalidResource(ConstraintViolationExceptions e, 
			HttpServletRequest request){
		String error = "Invalid resource ";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
