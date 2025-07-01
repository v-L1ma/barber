package com.barber.barber.application.usecases.deletarAgendamento;

import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoResponseDto;

public interface IDeletarAgendamentoUseCase {

    public CadastrarAgendamentoResponseDto executar(int id);

}
