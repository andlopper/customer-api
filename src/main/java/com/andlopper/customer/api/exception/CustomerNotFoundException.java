package com.andlopper.customer.api.exception;

//TODO ajustar dependencias do projeto para fazer a classe extends HttpException
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
