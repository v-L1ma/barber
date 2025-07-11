package com.barber.barber.domain.exceptions;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException(String message) {
        super(message);
    }

    public SenhaInvalidaException(){
      super("Senha inválida, por favor verifique sua senha");
    }
}
