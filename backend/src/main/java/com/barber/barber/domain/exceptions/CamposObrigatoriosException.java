package com.barber.barber.domain.exceptions;

public class CamposObrigatoriosException extends RuntimeException{

    public CamposObrigatoriosException(){
        super("Todos os campos são obrigatórios.");
    }

    public CamposObrigatoriosException(String message) {
        super(message);
    }

}