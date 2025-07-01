package com.barber.barber.domain.exceptions;

public class AgendamentoNaoPodeSerNoPassadoException extends RuntimeException{
    public AgendamentoNaoPodeSerNoPassadoException(){
        super("O agendamento não pode ser criado para uma data no passado");
    }

    public AgendamentoNaoPodeSerNoPassadoException(String message){
        super(message);
    }
}
