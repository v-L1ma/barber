package com.barber.barber.application.usecases.listarAgendamentosPorIdCliente;

import com.barber.barber.infra.web.DTOs.ListarAgendamentoResponseDTO;

public interface IListarAgendamentosPorIdClienteUseCase {
    ListarAgendamentoResponseDTO executar(int id);
}
