package com.medeiros.apiNumerosAleatorios.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	
	public ResourceNotFoundException (Object id) {
		super("Resorce not found. ID "+ id);
	}

}
