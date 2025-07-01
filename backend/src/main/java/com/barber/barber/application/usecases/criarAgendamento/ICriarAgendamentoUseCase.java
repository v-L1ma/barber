package com.barber.barber.application.usecases.criarAgendamento;

import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoResponseDto;

public interface ICriarAgendamentoUseCase {

    public CadastrarAgendamentoResponseDto executar(CadastrarAgendamentoDto agendamentoDto);
    
}
