package com.sysman.prueba.exceptions;

public class CiudadException extends RuntimeException {

    public CiudadException(String message) {
        super(message);
    }

    public CiudadException(String message, Throwable cause) {
        super(message, cause);
    }
}
