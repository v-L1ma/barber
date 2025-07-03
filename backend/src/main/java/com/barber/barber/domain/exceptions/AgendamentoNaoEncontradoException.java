package com.barber.barber.domain.exceptions;

public class AgendamentoNaoEncontradoException extends RuntimeException{

    public AgendamentoNaoEncontradoException(){
        super("Agendamento fornecido não encontrado.");
    }

    public AgendamentoNaoEncontradoException(String message) {
        super(message);
    }

}