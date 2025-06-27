package com.barber.barber.application.usecases.criarAgendamento;

public interface ICriarAgendamentoUseCase {

    public CadastrarAgendamentoResponseDto executar(CadastrarAgendamentoDto agendamentoDto);
    
}
