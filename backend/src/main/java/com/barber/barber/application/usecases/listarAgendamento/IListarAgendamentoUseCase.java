package com.barber.barber.application.usecases.listarAgendamento;


import com.barber.barber.infra.web.DTOs.ListarAgendamentoResponseDTO;

public interface IListarAgendamentoUseCase {

    ListarAgendamentoResponseDTO executar();

}
