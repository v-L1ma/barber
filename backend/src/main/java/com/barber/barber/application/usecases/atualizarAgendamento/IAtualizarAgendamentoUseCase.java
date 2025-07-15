package com.barber.barber.application.usecases.atualizarAgendamento;

import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoResponseDto;

public interface IAtualizarAgendamentoUseCase {
    public CadastrarAgendamentoResponseDto executar(int id, CadastrarAgendamentoDto agendamentoNovo);
}
