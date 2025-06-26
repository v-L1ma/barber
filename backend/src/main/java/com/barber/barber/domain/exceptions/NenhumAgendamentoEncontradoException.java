package com.barber.barber.domain.exceptions;

public class NenhumAgendamentoEncontradoException extends RuntimeException{

    public NenhumAgendamentoEncontradoException(){
        super("Nenhum agendamento encontrado.");
    }

    public NenhumAgendamentoEncontradoException(String message) {
        super(message);
    }

}
