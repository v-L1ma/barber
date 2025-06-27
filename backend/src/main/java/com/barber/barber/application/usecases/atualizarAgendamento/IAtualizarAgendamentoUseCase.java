package com.barber.barber.application.usecases.atualizarAgendamento;

public interface IAtualizarAgendamentoUseCase {
    public CadastrarAgendamentoResponseDto executar(int id, Agendamento agendamentoNovo);
}
