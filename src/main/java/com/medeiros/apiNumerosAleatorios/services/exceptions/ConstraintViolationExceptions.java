package com.medeiros.apiNumerosAleatorios.services.exceptions;

public class ConstraintViolationExceptions extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public ConstraintViolationExceptions(Object email) {
        super("Invalid resources " + email);
    }

}
