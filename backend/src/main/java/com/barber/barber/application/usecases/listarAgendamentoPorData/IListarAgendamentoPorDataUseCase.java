package com.barber.barber.application.usecases.listarAgendamentoPorData;

import com.barber.barber.infra.web.DTOs.ListarAgendamentoResponseDTO;

import java.time.LocalDate;

public interface IListarAgendamentoPorDataUseCase {

    ListarAgendamentoResponseDTO executar(LocalDate data);
    
}
