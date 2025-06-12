package com.barber.barber.DTOs;

import com.barber.barber.model.Agendamento.Agendamento;

import java.util.List;

public record ListarAgendamentoResponseDTO(
        String message,
        List<Agendamento> agendamentos
) {
}
