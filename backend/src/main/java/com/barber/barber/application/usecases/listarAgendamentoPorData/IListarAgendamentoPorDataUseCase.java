package com.barber.barber.application.usecases.listarAgendamentoPorData;

public interface IListarAgendamentoPorDataUseCase {

    ListarAgendamentoResponseDTO executar(LocalDate data);
    
}
