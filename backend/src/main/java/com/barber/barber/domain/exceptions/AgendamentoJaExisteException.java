package com.barber.barber.domain.exceptions;

public class AgendamentoJaExisteException extends RuntimeException{

    public AgendamentoJaExisteException(){
        super("Já existe um agendamento nessa data e nesse horário.");
    }

    public AgendamentoJaExisteException(String message) {
        super(message);
    }
}
