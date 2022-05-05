package com.examen.Exception;

public class ProductoAlreadyExistsException extends Exception{
    private String message;

    public ProductoAlreadyExistsException(){

    }

    public ProductoAlreadyExistsException(String message){
        super();
        this.message = message;
    }

}
