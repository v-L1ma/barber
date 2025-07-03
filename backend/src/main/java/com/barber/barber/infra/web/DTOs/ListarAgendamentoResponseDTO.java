package com.barber.barber.infra.web.DTOs;

import com.barber.barber.domain.entities.Agendamento.Agendamento;

import java.util.List;

public record ListarAgendamentoResponseDTO(
        String message,
        List<Agendamento> agendamentos
) {
}
