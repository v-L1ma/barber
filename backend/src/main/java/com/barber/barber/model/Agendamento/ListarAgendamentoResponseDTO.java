package com.barber.barber.model.Agendamento;

import java.util.List;

public record ListarAgendamentoResponseDTO(
        String message,
        List<Agendamento> agendamentos
) {
}
