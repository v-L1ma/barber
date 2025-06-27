package com.barber.barber.domain.exceptions;

public class CamposObrigatoriosException extends RuntimeException{

    public AgendamentoNaoEncontradoException(){
        super("Todos os campos são obrigatórios.");
    }

    public AgendamentoNaoEncontradoException(String message) {
        super(message);
    }

}